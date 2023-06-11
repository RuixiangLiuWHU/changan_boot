package whu.edu.cn.service;

import org.springframework.stereotype.Service;
import whu.edu.cn.entity.Route;
import whu.edu.cn.mapper.TextEventMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TextEventService {
    @Resource
    TextEventMapper textEventMapper;

    Route getNearestRouteofPOI(String routename, String poiname){
        Route route = textEventMapper.getNearestRouteofPOI(routename, poiname);
        return route;
    }
    List<Route> getRouteByName(String routeName){
        List<Route> routelist = textEventMapper.getRouteByName(routeName);
        return routelist;
    }
    double getM(String routegeom, String poiname){
        double d = textEventMapper.getM(routegeom, poiname);
        return d;
    }
    double getLength(String routegeom){
        double d = textEventMapper.getLength(routegeom);
        return d;
    }
    String getPointGeom(String routegeom, double m){
        String string = textEventMapper.getPointGeom(routegeom, m);
        return string;
    }
    String getSubLineGeom(String routegeom, double fromm, double tom){
        String string = textEventMapper.getSubLineGeom(routegeom, fromm, tom);
        return string;
    }
}
