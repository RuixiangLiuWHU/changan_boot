package whu.edu.cn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import whu.edu.cn.entity.POI;

import java.util.List;

@Mapper
public interface POIMapper {
    @Select("select st_astext(geom) as geom, * from osm.poi_affected0720 where fclass = Any(#{ids})")
    List<POI> getSelectPOIAffected0720(Integer[] ids);
    @Select("select st_astext(geom) as geom, * from osm.poi_affected0722 where fclass = Any(#{ids})")
    List<POI> getSelectPOIAffected0722(Integer[] ids);
    @Select("select count(*) from osm.poi where fclass = Any(#{ids})")
    Integer getSelectPOINum(Integer[] ids);
    @Select("select st_astext(geom) as geom, * from osm.poi where fclass = Any(#{ids})")
    List<POI> getSelectPOI(Integer[] ids);
    @Select("select st_astext(geom) as geom from henanflood0720 where #{geohash}= Any(geohash)")
    List<String> getPOIFlood0720(String geohash);
    @Select("select st_astext(geom) as geom from henanflood0722 where #{geohash}= Any(geohash)")
    List<String> getPOIFlood0722(String geohash);
    @Select("select st_intersects('${geom1}', '${geom2}')")
    Boolean getIntersects(String geom1, String geom2);
    @Select("TRUNCATE table osm.poi_affected0720")
    void clearPOIAffected0720();
    @Select("TRUNCATE table osm.poi_affected0722")
    void clearPOIAffected0722();
    @Insert("insert into osm.poi_affected0720(gid, name, address, lng, lat, industryclass, industrysubclass, geom, geohash, fclass) " +
            "VALUES(${gid}, #{name}, '${address}', ${lng}, ${lat}, '${industryclass}', '${industrysubclass}', " +
            "st_geomfromtext('${geom}',4326), '${geohash}', ${fclass})")
    void insertPOIAffected0720(Integer gid, String name, String address, double lng, double lat, String industryclass, String industrysubclass,
                   String geom, String geohash, Integer fclass);
    @Insert("insert into osm.poi_affected0722(gid, name, address, lng, lat, industryclass, industrysubclass, geom, geohash, fclass) " +
            "VALUES(${gid}, #{name}, '${address}', ${lng}, ${lat}, '${industryclass}', '${industrysubclass}', " +
            "st_geomfromtext('${geom}',4326), '${geohash}', ${fclass})")
    void insertPOIAffected0722(Integer gid, String name, String address, double lng, double lat, String industryclass, String industrysubclass,
                               String geom, String geohash, Integer fclass);

    @Select("select count(*) from osm.poi_edu")
    Integer getEduPOINum();
    @Select("select st_astext(geom) as geom, * from osm.poi_edu where gid = ${gid}")
    POI getEduPOI(Integer gid);
    @Select("select count(*) from osm.poi_trans")
    Integer getTransPOINum();
    @Select("select st_astext(geom) as geom, * from osm.poi_trans where gid = ${gid}")
    POI getTransPOI(Integer gid);
    @Select("select count(*) from osm.poi_hotel")
    Integer getHotelPOINum();
    @Select("select st_astext(geom) as geom, * from osm.poi_hotel where gid = ${gid}")
    POI getHotelPOI(Integer gid);
    @Select("select count(*) from osm.poi_view")
    Integer getViewPOINum();
    @Select("select st_astext(geom) as geom, * from osm.poi_view where gid = ${gid}")
    POI getViewPOI(Integer gid);
    @Select("select count(*) from osm.poi_medical")
    Integer getMedicalPOINum();
    @Select("select st_astext(geom) as geom, * from osm.poi_medical where gid = ${gid}")
    POI getMedicalPOI(Integer gid);
    @Select("select count(*) from osm.poi_adm")
    Integer getAdmPOINum();
    @Select("select st_astext(geom) as geom, * from osm.poi_adm where gid = ${gid}")
    POI getAdmPOI(Integer gid);
    @Insert("insert into osm.poi(gid, name, address, lng, lat, industryclass, industrysubclass, geom, geohash, fclass) " +
            "VALUES(${gid}, #{name}, '${address}', ${lng}, ${lat}, '${industryclass}', '${industrysubclass}', " +
            "st_geomfromtext('${geom}',4326), '${geohash}', ${fclass})")
    void insertPOI(Integer gid, String name, String address, double lng, double lat, String industryclass, String industrysubclass,
                   String geom, String geohash, Integer fclass);
    @Select("select name from osm.poi where name is not null")
    List<String> getPOIName();

    @Select("select st_astext(geom) as geom, * from osm.poi where name = #{name}")
    POI getPOIByName(String name);
}
