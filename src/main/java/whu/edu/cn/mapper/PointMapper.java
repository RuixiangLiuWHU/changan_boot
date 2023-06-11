package whu.edu.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.postgis.LineString;
import org.postgis.MultiLineString;
import org.postgis.Point;
import whu.edu.cn.entity.PointEvent;
import whu.edu.cn.entity.Route;

@Mapper
public interface PointMapper extends BaseMapper<PointEvent> {
    @Select("select * from ways order by the_geom <-> st_geomfromtext('${pointWKT}',4326) limit 1")
    Integer getNearestRouteId(String pointWKT);

    @Select("select st_astext(the_geom) from ways where gid=#{routeid}")
    String selectRouteGeom(Integer routeid);

    @Select("select * from ST_linelocatepoint(st_geomfromtext('${geom}',4326), st_geomfromtext('${geo}',4326))")
    double getPointM(String geom, String geo);

    @Select("select st_astext(st_lineinterpolatepoint('${geom}', ${m}))")
    String getPointonLine(String geom, double m);

    @Select("select * from st_astext(ST_lineinterpolatepoint(st_geomfromtext('${geom}',4326), ${M}))")
    String getPointGeom(String geom, double M);

    @Select("select id, m, st_astext(geom) as geom, disasterid, routeid from point where id = ${id}")
    PointEvent selectPointEvent(Integer id);

    @Insert("insert into point(m,geom,disasterid,routeid) VALUES(${m}, st_geomfromtext('${geom}',4326), ${disasterid}, ${routeid})")
    void insertPointEvent(double m, String geom, Integer disasterid, Integer routeid);

    @Select("select * from point order by id desc limit 1")
    Integer getlastpoint();
}
