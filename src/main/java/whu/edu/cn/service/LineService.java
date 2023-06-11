package whu.edu.cn.service;

import org.springframework.stereotype.Service;
import whu.edu.cn.entity.Route;
import whu.edu.cn.mapper.LineMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LineService {
    @Resource
    LineMapper lineMapper;

    public int getRouteNum() {
        return lineMapper.getRouteNum();
    }

    public Route getNearestRouteId(double x, double y) {
        Route route = lineMapper.getNearestRouteId(x, y);
        return route;
    }

    String getRouteGeom(Integer id) {
        String string = lineMapper.getRouteGeom(id);
        return string;
    }

    double getLineM(String geom, String geo) {
        double d = lineMapper.getLineM(geom, geo);
        return d;
    }

    String getLineGeom(String geom, double fromm, double tom) {
        String string = lineMapper.getLineGeom(geom, fromm, tom);
        return string;
    }

    List<String> getRouteName() {
        List<String> stringList = lineMapper.getRouteName();
        return stringList;
    }

    List<Route> getRouteByName(String name) {
        List<Route> routeList = lineMapper.getRouteByName(name);
        return routeList;
    }
}
