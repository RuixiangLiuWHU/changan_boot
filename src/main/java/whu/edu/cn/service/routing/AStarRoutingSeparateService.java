package whu.edu.cn.service.routing;

import org.springframework.stereotype.Service;
import whu.edu.cn.mapper.routing.AStarRoutingSeparateMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class AStarRoutingSeparateService {
    @Resource
    AStarRoutingSeparateMapper aStarRoutingSeparateMapper;
    public Integer getStartOrEnd(double x, double y){
        Integer integer = aStarRoutingSeparateMapper.getStartOrEnd(x, y);
        return integer;
    }
    double getlat(Integer id){
        double d = aStarRoutingSeparateMapper.getlat(id);
        return d;
    }
    double geton(Integer id){
        double d = aStarRoutingSeparateMapper.getlon(id);
        return d;
    }
    List<Integer> getTarget(Integer id){
        List<Integer> integers = aStarRoutingSeparateMapper.getTarget(id);
        return integers;
    }
    String getAStarRoutingSeparateGeom(Integer start, Integer end){
        String string = aStarRoutingSeparateMapper.getAStarRoutingSeparateGeom(start, end);
        return string;
    }
    void clearAStarRoutingSeparate(){};
}
