package whu.edu.cn.mapper.routing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import whu.edu.cn.entity.routing.AStarRoutingSeparate;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AStarRoutingSeparateMapper extends BaseMapper<AStarRoutingSeparate> {
    @Select("SELECT id FROM ways_vertices_pgr ORDER BY the_geom <-> ST_SetSRID(ST_Point(${x}, ${y}), 4326) LIMIT 1")
    Integer getStartOrEnd(double x, double y);
    @Select("select lat from ways_vertices_pgr where id = ${id}")
    double getlat(Integer id);
    @Select("select lon from ways_vertices_pgr where id = ${id}")
    double getlon(Integer id);
    @Select("select target from ways where source = ${id}")
    List<Integer> getTarget(Integer id);
    @Select("select st_astext(the_geom) from ways where source=${start} and target=${end}")
    String getAStarRoutingSeparateGeom(Integer start, Integer end);
    @Update("TRUNCATE TABLE astarroutingseparate")
    void clearAStarRoutingSeparate();
    @Insert("insert into astarroutingseparate(source, target, geom) VALUES(${start}, ${end}, st_geomfromtext('${geom}',4326))")
    void insertAStarRoutingSeparate(Integer start, Integer end, String geom);
}
