package whu.edu.cn.mapper.aftermatching;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import whu.edu.cn.entity.aftermatching.PointRouteAM;

@Mapper
public interface PointRouteAMMapper extends BaseMapper<PointRouteAM> {

    @Insert("insert into pointrouteam(id, geom,disasterid,routeid) VALUES(${id}, st_geomfromtext('${geom}',4326), ${disasterid}, ${routeid})")
    void insertPointRouteAM(Integer id, String geom, Integer disasterid, Integer routeid);
}
