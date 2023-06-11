package whu.edu.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import whu.edu.cn.entity.Disaster;
import whu.edu.cn.entity.LineEvent;
import whu.edu.cn.entity.PointEvent;

import java.util.List;

@Mapper
public interface DisasterMapper extends BaseMapper<Disaster>{
    @Select("select count(*) from disaster")
    Integer getDisasterNum();

    @Select("select * from disaster")
    List<Disaster> getAllDisaster();

    @Select("select count(*) from point where disasterid = ${id}")
    Integer getPointEventNum(Integer id);

    @Select("select st_astext(geom) as geom , * from point where disasterid = ${id}")
    List<PointEvent> getPointEventList(Integer id);

    @Select("select st_astext(geom) as geom from point where disasterid = ${id}")
    List<String> getPointEventListGeom(Integer id);

    @Select("select count(*) from line where disasterid = ${id}")
    Integer getLineEventNum(Integer id);

    @Select("select st_astext(geom) as geom , * from line where disasterid = ${id}")
    List<LineEvent> getLineEventList(Integer id);

    @Select("select st_astext(geom) as geom from line where disasterid = ${id}")
    List<String> getLineEventListGeom(Integer id);
}
