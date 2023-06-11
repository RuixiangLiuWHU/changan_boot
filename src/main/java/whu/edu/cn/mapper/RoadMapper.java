package whu.edu.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import whu.edu.cn.entity.Route;
import whu.edu.cn.entity.Vertices;

import java.util.List;

@Mapper
public interface RoadMapper {
    @Select("select gid, name, source, target, cost, reverse_cost, x1, y1, x2, y2, maxspeed_forward, maxspeed_backward\n" +
            ", priority, st_astext(the_geom) as geom, geohash7, geohash6, geohash5, geohash4, geohash3, geohash from ways")
    List<Route> getAllRoads();

    @Select("select id, lon, lat, st_astext(the_geom) as geom from ways_vertices_pgr")
    List<Vertices> getAllVertices();
}
