package whu.edu.cn.controller.routing;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.operation.linemerge.LineMerger;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whu.edu.cn.entity.Road;
import whu.edu.cn.entity.routing.PGRouting;
import whu.edu.cn.mapper.RoadMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "图数据库：最短路径规划管理接口")
public class Neo4jController {

    @Autowired
    RoadMapper roadMapper;

    private final Driver neo4jDriver;

    @Autowired
    public Neo4jController(Driver neo4jDriver) {
        this.neo4jDriver = neo4jDriver;
    }

    @ApiOperation(value = "图数据库：基于灾害事件，获取最短路径规划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kind", value = "最短路径规划类别", required = true),
            @ApiImplicitParam(name = "x1", value = "起点经度", required = true),
            @ApiImplicitParam(name = "y1", value = "起点纬度", required = true),
            @ApiImplicitParam(name = "x2", value = "终点经度", required = true),
            @ApiImplicitParam(name = "y2", value = "终点纬度", required = true),
            @ApiImplicitParam(name = "disasterid", value = "灾害ID", required = true)
    })
    @GetMapping("/getroute")
    public PGRouting findShortestPath(Integer kind, double x1, double y1, double x2, double y2, Integer disasterid) {
        if (disasterid != 0) {
            List<Integer> integerList = roadMapper.getRouteID(disasterid);
            for (Integer integer : integerList) {
                Long source = roadMapper.getSource(integer);
                Long target = roadMapper.getTarget(integer);
                if (kind == 1) {
                    updateLengthByRoadId(source, target, integer.longValue(), 10000000000000.0);
                } else if (kind == 2) {
                    updateCostByRoadId(source, target, integer.longValue(), 10000000000000.0);
                } else if (kind == 3) {
                    updatePriorityByRoadId(source, target, integer.longValue(), 10000000000000.0);
                }
            }
        }

        Long startVertexId = roadMapper.getStartVertexId(x1, y1);
        Long endVertexId = roadMapper.getEndVertexId(x2, y2);

        String cql = "";
        if (kind == 1) {
            cql = "MATCH (start:vertex {vertexId: " + "'" + startVertexId.toString() + "'" + "}) " +
                    "OPTIONAL MATCH (end:vertex {vertexId: " + "'" + endVertexId.toString() + "'" + "}) " +
                    "CALL apoc.algo.dijkstra(start, end, 'road', 'length') YIELD path\n" +
                    "UNWIND nodes(path) AS node\n" +
                    "WITH collect(node) AS nodes\n" +
                    "UNWIND range(0, size(nodes) - 2) AS index\n" +
                    "WITH nodes[index] AS startNode, nodes[index+1] AS endNode\n" +
                    "MATCH (startNode)-[rel:road]->(endNode)\n" +
                    "RETURN startNode.vertexId AS startVertexId, rel.roadId AS roadId, rel.length AS length, rel.cost AS cost, endNode.vertexId AS endVertexId";
        } else if (kind == 2) {
            cql = "MATCH (start:vertex {vertexId: " + "'" + startVertexId.toString() + "'" + "}) " +
                    "OPTIONAL MATCH (end:vertex {vertexId: " + "'" + endVertexId.toString() + "'" + "}) " +
                    "CALL apoc.algo.dijkstra(start, end, 'road', 'cost') YIELD path\n" +
                    "UNWIND nodes(path) AS node\n" +
                    "WITH collect(node) AS nodes\n" +
                    "UNWIND range(0, size(nodes) - 2) AS index\n" +
                    "WITH nodes[index] AS startNode, nodes[index+1] AS endNode\n" +
                    "MATCH (startNode)-[rel:road]->(endNode)\n" +
                    "RETURN startNode.vertexId AS startVertexId, rel.roadId AS roadId, rel.length AS length, rel.cost AS cost, endNode.vertexId AS endVertexId";
        } else if (kind == 3) {
            cql = "MATCH (start:vertex {vertexId: " + "'" + startVertexId.toString() + "'" + "}) " +
                    "OPTIONAL MATCH (end:vertex {vertexId: " + "'" + endVertexId.toString() + "'" + "}) " +
                    "CALL apoc.algo.dijkstra(start, end, 'road', 'priority') YIELD path\n" +
                    "UNWIND nodes(path) AS node\n" +
                    "WITH collect(node) AS nodes\n" +
                    "UNWIND range(0, size(nodes) - 2) AS index\n" +
                    "WITH nodes[index] AS startNode, nodes[index+1] AS endNode\n" +
                    "MATCH (startNode)-[rel:road]->(endNode)\n" +
                    "RETURN startNode.vertexId AS startVertexId, rel.roadId AS roadId, rel.length AS length, rel.cost AS cost, endNode.vertexId AS endVertexId";
        }

        try (Session session = neo4jDriver.session()) {
            Result result = session.run(cql);

            List<Road> roadList = new ArrayList<>();
            int counter = 1;
            double lengthAll = 0.0;
            double costAll = 0.0;
            StringBuilder geomAll = new StringBuilder();

            while (result.hasNext()) {
                Record record = result.next();

                String startNode = record.get("startVertexId").asString();
                String roadId = record.get("roadId").asString();
                String endNode = record.get("endVertexId").asString();
                double length = Double.parseDouble(record.get("length").asString());
                double cost = Double.parseDouble(record.get("cost").asString());
                // TODO 这里要选择性取反
                Long source = roadMapper.getSource(Integer.parseInt(roadId));
                Long target = roadMapper.getTarget(Integer.parseInt(roadId));
                String geom;
                if (Long.parseLong(startNode) == source && Long.parseLong(endNode) == target) {
                    geom = roadMapper.getGeom(Integer.parseInt(roadId));
                } else if (Long.parseLong(startNode) == target && Long.parseLong(endNode) == source) {
                    geom = roadMapper.getReverseGeom(Integer.parseInt(roadId));
                } else {
                    throw new InternalError("Neo4j的起点和终点与数据库中的起点和终点不一致");
                }

                Road road = new Road();
                road.setId(String.valueOf(counter));
                road.setStartVertexId(startNode);
                road.setRoadId(roadId);
                road.setEndVertexId(endNode);
                road.setGeom(geom);
                road.setLength(length);
                road.setCost(cost);
                roadList.add(road);

                lengthAll += length;
                costAll += cost;
                if (counter == 1) {
                    geomAll.append(geom.replace("LINESTRING(", "").replace(" ", ",").replace(")", ""));
                } else {
                    geomAll.append(geom.replace(geom.split(",")[0], "").replace(")", "").replace(" ", ","));
                }
                counter++;
            }

            PGRouting pgRouting = new PGRouting();
            pgRouting.setLength(lengthAll / 1000.0);
            pgRouting.setTiming(costAll / 60.0);
            pgRouting.setGeom(geomAll.toString());


            if (disasterid != 0) {
                List<Integer> integerList = roadMapper.getRouteID(disasterid);
                for (Integer integer : integerList) {
                    if (kind == 1) {
                        double lengthOrigin = roadMapper.getLength(integer);
                        updateLengthByRoadId(roadMapper.getSource(integer), roadMapper.getTarget(integer), integer.longValue(), lengthOrigin);

                    } else if (kind == 2) {
                        double costOrigin = roadMapper.getCost(integer);
                        updateCostByRoadId(roadMapper.getSource(integer), roadMapper.getTarget(integer), integer.longValue(), costOrigin);

                    } else if (kind == 3) {
                        double priorityOrigin = roadMapper.getPriority(integer);
                        updatePriorityByRoadId(roadMapper.getSource(integer), roadMapper.getTarget(integer), integer.longValue(), priorityOrigin);
                    }
                }
            }
            return pgRouting;
        }
    }

    public void updateLengthByRoadId(Long startVertexId, Long endVertexId, Long roadId, double length) {

        String cql = "MATCH (v1:vertex {vertexId: '" + startVertexId.toString() + "'})" + "" +
                "-[r:road {roadId: '" + roadId.toString() + "'}]" +
                "-(v2:vertex {vertexId: '" + endVertexId.toString() + "'})\n" +
                "SET r.length = '" + length + "'";

        try (Session session = neo4jDriver.session()) {
            session.run(cql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCostByRoadId(Long startVertexId, Long endVertexId, Long roadId, double cost) {

        String cql = "MATCH (v1:vertex {vertexId: '" + startVertexId.toString() + "'})" + "" +
                "-[r:road {roadId: '" + roadId.toString() + "'}]" +
                "-(v2:vertex {vertexId: '" + endVertexId.toString() + "'})\n" +
                "SET r.cost = '" + cost + "'";

        try (Session session = neo4jDriver.session()) {
            session.run(cql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePriorityByRoadId(Long startVertexId, Long endVertexId, Long roadId, double priority) {

        String cql = "MATCH (v1:vertex {vertexId: '" + startVertexId.toString() + "'})" + "" +
                "-[r:road {roadId: '" + roadId.toString() + "'}]" +
                "-(v2:vertex {vertexId: '" + endVertexId.toString() + "'})\n" +
                "SET r.priority = '" + priority + "'";

        try (Session session = neo4jDriver.session()) {
            session.run(cql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
