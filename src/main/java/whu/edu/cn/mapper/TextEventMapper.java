package whu.edu.cn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import whu.edu.cn.entity.Route;

import java.util.List;

@Mapper
public interface TextEventMapper {
    @Select("select st_astext(the_geom) as geom, * from ways where name=#{routename} order by the_geom <-> (select geom from osm.poi where name=#{poiname}) limit 1")
    Route getNearestRouteofPOI(String routename, String poiname);
    @Select("select st_astext(the_geom) as geom, * from ways where name=#{routename}")
    List<Route> getRouteByName(String routeName);
    @Select("select st_linelocatepoint(st_geomfromtext('${routegeom}',4326), (select geom from osm.poi where name=#{poiname}))")
    double getM(String routegeom, String poiname);
    @Select("select st_length(st_geomfromtext('${routegeom}',4326)::geography)")
    double getLength(String routegeom);
    @Select("select st_astext(st_lineinterpolatepoint(st_geomfromtext('${routegeom}',4326), #{m}))")
    String getPointGeom(String routegeom, double m);
    @Select("select st_astext(st_linesubstring('${routegeom}',#{fromm},#{tom}))")
    String getSubLineGeom(String routegeom, double fromm, double tom);
    @Insert("insert into testline(id, geom) VALUES(#{id}, st_geomfromtext('${geom}',4326))")
    void insertTestLine(Integer id, String geom);
    @Select("TRUNCATE table testline")
    void truncateTable();
}

