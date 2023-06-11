package whu.edu.cn.mapper.routing;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import whu.edu.cn.entity.routing.PGRouting;

import java.util.List;

@Mapper
public interface PGRoutingMapper{
    @Select("SELECT st_astext(ST_MakeLine(geom)) as geom, sum(length)/1000.0 as length, sum(timing) as timing FROM ll_fromatobpriority('littleways',${x1},${y1},${x2},${y2},${speed})")
    PGRouting getPGRoutingPriority(double x1, double y1, double x2, double y2, double speed);
    @Select("SELECT st_astext(ST_MakeLine(geom)) as geom, sum(length)/1000.0 as length, sum(timing) as timing FROM ll_fromatobdistance('littleways',${x1},${y1},${x2},${y2},${speed})")
    PGRouting getPGRoutingDistance(double x1, double y1, double x2, double y2, double speed);
    @Select("SELECT st_astext(ST_MakeLine(geom)) as geom, sum(length)/1000.0 as length, sum(timing) as timing FROM ll_fromatobtime('littleways',${x1},${y1},${x2},${y2},${speed})")
    PGRouting getPGRoutingTime(double x1, double y1, double x2, double y2, double speed);
    @Select("select routeid from point where disasterid = ${disasterid} union select routeid from line where disasterid = ${disasterid}")
    List<Integer> getRouteID(Integer disasterid);
    @Update("update ways set cost = ${cost}, length_m = ${cost} where gid = ${id}")
    void updatecost(double cost, Integer id);
    @Update("update ways set cost = st_length(the_geom), length_m = st_length(st_setSRID(the_geom,4326)::geography) where gid = ${id}")
    void updatecostorigin(Integer id);
    @Select("SELECT x1 from ways where gid = ${id}")
    double getStartX(Integer id);
    @Select("SELECT y1 from ways where gid = ${id}")
    double getStartY(Integer id);
    @Update("update ways set geohash7 = '${geohash}' where gid = ${id}")
    void updateGeohash7(String geohash, Integer id);
    @Update("update ways set geohash6 = '${geohash}' where gid = ${id}")
    void updateGeohash6(String geohash, Integer id);
    @Update("update ways set geohash5 = '${geohash}' where gid = ${id}")
    void updateGeohash5(String geohash, Integer id);
    @Update("update ways set geohash4 = '${geohash}' where gid = ${id}")
    void updateGeohash4(String geohash, Integer id);
    @Update("update ways set geohash3 = '${geohash}' where gid = ${id}")
    void updateGeohash3(String geohash, Integer id);
    @Select("CREATE OR REPLACE VIEW littleways AS\n" +
            "\tSELECT *\n" +
            "\tFROM ways\n" +
            "where geohash7 in (select geohash7 from ways where st_intersects(the_geom,st_buffer(ST_MakeLine(ST_SetSRID(ST_Point(${x1},${y1}), 4326),ST_SetSRID(ST_Point(${x2},${y2}), 4326)),0.0015)))")
    void createview72(double x1, double y1, double x2, double y2);
    @Select("CREATE OR REPLACE VIEW littleways AS\n" +
            "\tSELECT *\n" +
            "\tFROM ways\n" +
            "where geohash7 in (select geohash7 from ways where st_intersects(the_geom,st_buffer(ST_MakeLine(ST_SetSRID(ST_Point(${x1},${y1}), 4326),ST_SetSRID(ST_Point(${x2},${y2}), 4326)),0.045)))")
    void createview7(double x1, double y1, double x2, double y2);
    @Select("CREATE OR REPLACE VIEW littleways AS\n" +
            "\tSELECT *\n" +
            "\tFROM ways\n" +
            "where geohash6 in (select geohash6 from ways where st_intersects(the_geom,st_buffer(ST_MakeLine(ST_SetSRID(ST_Point(${x1},${y1}), 4326),ST_SetSRID(ST_Point(${x2},${y2}), 4326)),0.135)))")
    void createview6(double x1, double y1, double x2, double y2);
    @Select("CREATE OR REPLACE VIEW littleways AS\n" +
            "\tSELECT *\n" +
            "\tFROM ways\n" +
            "where geohash5 in (select geohash5 from ways where st_intersects(the_geom,st_buffer(ST_MakeLine(ST_SetSRID(ST_Point(${x1},${y1}), 4326),ST_SetSRID(ST_Point(${x2},${y2}), 4326)),1.124)))")
    void createview5(double x1, double y1, double x2, double y2);
    @Select("CREATE OR REPLACE VIEW littleways AS\n" +
            "\tSELECT *\n" +
            "\tFROM ways\n" +
            "where geohash4 in (select geohash4 from ways where st_intersects(the_geom,st_buffer(ST_MakeLine(ST_SetSRID(ST_Point(${x1},${y1}), 4326),ST_SetSRID(ST_Point(${x2},${y2}), 4326)),4.047)))")
    void createview4(double x1, double y1, double x2, double y2);
    @Select("CREATE OR REPLACE VIEW littleways AS\n" +
            "\tSELECT *\n" +
            "\tFROM ways\n" +
            "where geohash3 in (select geohash3 from ways where st_intersects(the_geom,st_buffer(ST_MakeLine(ST_SetSRID(ST_Point(${x1},${y1}), 4326),ST_SetSRID(ST_Point(${x2},${y2}), 4326)),7.1945)))")
    void createview3(double x1, double y1, double x2, double y2);

}
