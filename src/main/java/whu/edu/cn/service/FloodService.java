package whu.edu.cn.service;

import org.springframework.stereotype.Service;
import whu.edu.cn.entity.Route;
import whu.edu.cn.mapper.FloodMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FloodService {
    @Resource
    FloodMapper floodMapper;

    Route getRoute(Integer id) {
        Route route = floodMapper.getRoute(id);
        return route;
    }
    String getFlood(Integer id) {
        String string = floodMapper.getFlood(id);
        return string;
    }
    Boolean getIntersects(String geom1, String geom2) {
        Boolean b = floodMapper.getIntersects(geom1, geom2);
        return b;
    }
    String getIntersection(String geom1, String geom2) {
        String string = floodMapper.getIntersection(geom1, geom2);
        return string;
    }
    String getMBR(String geom) {
        String string = floodMapper.getMBR(geom);
        return string;
    }
    double getMBRLeftDownX(String geom) {
        double d = floodMapper.getMBRLeftDownX(geom);
        return d;
    }
    double getMBRLeftDownY(String geom) {
        double d = floodMapper.getMBRLeftDownY(geom);
        return d;
    }
    double getMBRRightUpX(String geom) {
        double d = floodMapper.getMBRRightUpX(geom);
        return d;
    }
    double getMBRRightUpY(String geom) {
        double d = floodMapper.getMBRRightUpY(geom);
        return d;
    }
    Object getFloodGeohash(Integer id) {
        Object string = floodMapper.getFloodGeohash(id);
        return string;
    }
    Object getRouteGeohash(Integer id) {
        Object string = floodMapper.getRouteGeohash(id);
        return string;
    }
    List<Route> getGeoRoute(String string) {
        List<Route> routes = floodMapper.getGeoRoute(string);
        return routes;
    }
    double getLength(String geom) {
        double d = floodMapper.getLength(geom);
        return d;
    }
    String getStartPoint(String geom) {
        String string = floodMapper.getStartPoint(geom);
        return string;
    }
    String getEndPoint(String geom) {
        String string = floodMapper.getEndPoint(geom);
        return string;
    }
    String getCentroid(String geom) {
        String string = floodMapper.getCentroid(geom);
        return string;
    }
    String[] dumpMultiLineString(String geom) {
        String[] strings = floodMapper.dumpMultiLineString(geom);
        return strings;
    }
}
