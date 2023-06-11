package whu.edu.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import whu.edu.cn.entity.LineEvent;
import whu.edu.cn.entity.Route;

import java.util.List;

@Mapper
public interface LineMapper extends BaseMapper<LineEvent> {

    @Select("select count(*) from ways")
    int getRouteNum();

    @Select("select *, st_astext(the_geom) as geom from ways order by the_geom <-> st_geomfromtext('Point(${x} ${y})',4326) limit 1")
    Route getNearestRouteId(double x, double y);

    @Select("select st_astext(the_geom) from ways where gid = ${id}")
    String getRouteGeom(Integer id);

    @Select("select * from ST_linelocatepoint(st_geomfromtext('${geom}',4326), st_geomfromtext('${geo}',4326))")
    double getLineM(String geom, String geo);

    @Select("select * from ST_linelocatepoint(st_geomfromtext('${geom}',4326), ST_SetSRID(ST_Point(${x}, ${y}), 4326))")
    double getLineMByXY(String geom, double x, double y);

    @Select("select st_astext(st_lineinterpolatepoint('${geom}', ${m}))")
    String getPointonLine(String geom, double m);

    @Select("select st_astext(st_linesubstring(st_geomfromtext('${geom}',4326), ${fromm}, ${tom}))")
    String getLineGeom(String geom, double fromm, double tom);

    @Insert("insert into line(fromm, tom, geom, disasterid, routeid) VALUES(${fromm}, ${tom}, st_geomfromtext('${geom}',4326), ${disasterid}, ${routeid})")
    void insertLine(double fromm, double tom, String geom, Integer disasterid, Integer routeid);

    @Select("select name from ways where name is not null")
    List<String> getRouteName();

    @Select("select *, st_astext(the_geom) as geom from ways where name = #{name}")
    List<Route> getRouteByName(String name);

    @Select("select st_astext(st_startpoint(st_geomfromtext('${geom}',4326)))")
    String getSource(String geom);

    @Select("select st_astext(st_endpoint(st_geomfromtext('${geom}',4326)))")
    String getTarget(String geom);

    @Select("select st_astext(st_linemerge(st_difference(st_geomfromtext('${geom}',4326), st_geomfromtext('${geom2}',4326))))")
    String getDifference(String geom, String geom2);

    @Select("select st_astext(st_reverse(st_geomfromtext('${geom}',4326)))")
    String getReverse(String geom);

    @Select("select * from st_intersects(st_geomfromtext('${geom}',4326), st_geomfromtext('${geom2}',4326))")
    Boolean intersects(String geom, String geom2);

}
