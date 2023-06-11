package whu.edu.cn.service.routing;

import org.springframework.stereotype.Service;
import whu.edu.cn.entity.routing.PGRouting;
import whu.edu.cn.mapper.routing.PGRoutingMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PGRoutingService {
    @Resource
    PGRoutingMapper pgRoutingMapper;
    public PGRouting getPGRoutingPriority(double x1, double y1, double x2, double y2, double speed) {
        PGRouting pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
        return pgRouting;
    }
    public PGRouting getPGRoutingDistance(double x1, double y1, double x2, double y2, double speed) {
        PGRouting pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
        return pgRouting;
    }
    public PGRouting getPGRoutingTime(double x1, double y1, double x2, double y2, double speed) {
        PGRouting pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
        return pgRouting;
    }
    List<Integer> getRouteID(Integer disasterid){
        List<Integer> integerList = pgRoutingMapper.getRouteID(disasterid);
        return integerList;
    }
    double getStartX(Integer id){
        double d = pgRoutingMapper.getStartX(id);
        return d;
    }
    double getStartY(Integer id){
        double d = pgRoutingMapper.getStartY(id);
        return d;
    }
}

