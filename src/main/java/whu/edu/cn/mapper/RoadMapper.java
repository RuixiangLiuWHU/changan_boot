package whu.edu.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import whu.edu.cn.entity.Road;
import whu.edu.cn.entity.Route;
import whu.edu.cn.entity.Vertices;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface RoadMapper extends BaseMapper<Road> {
    @Select("select gid, name, source, target, cost, reverse_cost, x1, y1, x2, y2, maxspeed_forward, maxspeed_backward\n" +
            ", priority, st_astext(the_geom) as geom, geohash7, geohash6, geohash5, geohash4, geohash3, geohash from ways")
    List<Route> getAllRoads();

    @Select("select gid, name, source, target, cost, reverse_cost, x1, y1, x2, y2, maxspeed_forward, maxspeed_backward\n" +
            ", priority, st_astext(the_geom) as geom, geohash7, geohash6, geohash5, geohash4, geohash3, geohash from ways where name=#{name}")
    List<Route> getRoadsByName(String name);

    @Select("select gid, name, source, target, cost, reverse_cost, x1, y1, x2, y2, maxspeed_forward, maxspeed_backward\n" +
            ", priority, st_astext(the_geom) as geom, geohash7, geohash6, geohash5, geohash4, geohash3, geohash from ways where st_intersects(the_geom, ST_POLYGON('LINESTRING(${x1} ${y1}, ${x1} ${y2}, ${x2} ${y2}, ${x2} ${y1}, ${x1} ${y1})',4326))")
    List<Route> getRoadsByExtent(double x1, double x2, double y1, double y2);

    @Select("select id, lon, lat, st_astext(the_geom) as geom from ways_vertices_pgr")
    List<Vertices> getAllVertices();

    @Select("SELECT source FROM ways ORDER BY the_geom <-> ST_SetSRID(ST_Point('${x1}', '${y1}'), 4326) LIMIT 1")
    Long getStartVertexId(double x1, double y1);

    @Select("SELECT target FROM ways ORDER BY the_geom <-> ST_SetSRID(ST_Point('${x2}', '${y2}'), 4326) LIMIT 1")
    Long getEndVertexId(double x2, double y2);

    @Select("select routeid from point where disasterid = ${disasterid} union select routeid from line where disasterid = ${disasterid}")
    List<Integer> getRouteID(Integer disasterid);

    @Select("select source from ways where gid = ${routeid}")
    Long getSource(Integer routeid);

    @Select("select target from ways where gid = ${routeid}")
    Long getTarget(Integer routeid);

    @Select("select length_m from ways where gid = ${routeid}")
    double getLength(Integer routeid);

    @Select("select cost_s from ways where gid = ${routeid}")
    double getCost(Integer routeid);

    @Select("select priority from ways where gid = ${routeid}")
    double getPriority(Integer routeid);

    @Select("select st_astext(the_geom) as geom from ways where gid = ${routeid}")
    String getGeom(Integer routeid);

    @Select("select st_astext(st_reverse(the_geom)) as geom from ways where gid = ${routeid}")
    String getReverseGeom(Integer routeid);

}
