package whu.edu.cn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import whu.edu.cn.entity.Route;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface FloodMapper {
    @Select("select *, st_astext(the_geom) as geom from ways where gid = ${id}")
    Route getRoute(Integer id);

    @Select("select st_astext(geom) from henanflood0720 where gid = ${id}")
    String getFlood(Integer id);

    @Select("select *,st_astext(the_geom) as geom from ways where '${string}'= Any(geohash)")
    List<Route> getGeoRoute(String string);

    @Select("select st_intersects('${geom1}', '${geom2}')")
    Boolean getIntersects(String geom1, String geom2);

    @Select("select st_astext(st_intersection('${geom1}', '${geom2}'))")
    String getIntersection(String geom1, String geom2);

    @Update("update henanflood0722 set geohash = #{geohash} where gid = ${id}")
    void updateGeohash(String[] geohash, Integer id);

    @Update("update ways set geohash = #{geohash} where gid = ${id}")
    void updateRouteGeohash(String[] geohash, Integer id);

    @Select("select st_astext(st_envelope(st_buffer('${geom}',0.0000001)))")
    String getMBR(String geom);

    @Select("select st_x(st_pointn(st_exteriorring('${geom}'),1))")
    double getMBRLeftDownX(String geom);

    @Select("select st_y(st_pointn(st_exteriorring('${geom}'),1))")
    double getMBRLeftDownY(String geom);

    @Select("select st_x(st_pointn(st_exteriorring('${geom}'),3))")
    double getMBRRightUpX(String geom);

    @Select("select st_y(st_pointn(st_exteriorring('${geom}'),3))")
    double getMBRRightUpY(String geom);

    @Select("select geohash from henanflood0720 where gid = ${id}")
    Object getFloodGeohash(Integer id);

    @Select("select geohash from ways where gid = ${id}")
    Object getRouteGeohash(Integer id);

    @Select("select st_length('${geom}')")
    double getLength(String geom);

    @Select("select st_astext(st_startpoint('${geom}'))")
    String getStartPoint(String geom);

    @Select("select st_astext(st_endpoint('${geom}'))")
    String getEndPoint(String geom);

    @Select("select st_astext(st_centroid('${geom}'))")
    String getCentroid(String geom);

    @Select("select st_astext((st_dump('${geom}')).geom)")
    String[] dumpMultiLineString(String geom);
}
