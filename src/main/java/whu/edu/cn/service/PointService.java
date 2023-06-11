package whu.edu.cn.service;

import org.postgis.LineString;
import org.postgis.MultiLineString;
import org.postgis.Point;
import org.springframework.stereotype.Service;
import whu.edu.cn.entity.PointEvent;
import whu.edu.cn.entity.Route;
import whu.edu.cn.mapper.PointMapper;

import javax.annotation.Resource;

@Service
public class PointService {
    @Resource
    PointMapper pointMapper;

    public Integer getNearestRouteId(String pointWKT) {
        Integer route_id = pointMapper.getNearestRouteId(pointWKT);
        return route_id;
    }
    public PointEvent selectPointEvent(Integer id){
        PointEvent pointevent = pointMapper.selectPointEvent(id);
        return pointevent;
    }
    public String selectRouteGeom(Integer route_id){
        String routegeom = pointMapper.selectRouteGeom(route_id);
        return routegeom;
    }
    public double getPointM(String the_geom, String geo){
        double M = pointMapper.getPointM(the_geom, geo);
        return M;
    }
    public String getPointGeom(String the_geom, double M){
        String pointgeom = pointMapper.getPointGeom(the_geom, M);
        return pointgeom;
    }
    public Integer getlastpoint(){
        Integer id = pointMapper.getlastpoint();
        return id;
    }
    String getPointonLine(String geom, double m){
        String string = pointMapper.getPointonLine(geom, m);
        return string;
    }
}
