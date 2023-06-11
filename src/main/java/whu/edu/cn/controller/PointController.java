package whu.edu.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whu.edu.cn.entity.PointEvent;
import whu.edu.cn.mapper.PointMapper;
import whu.edu.cn.mapper.aftermatching.PointRouteAMMapper;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PointController {

    @Autowired
    PointMapper pointMapper;

    @Autowired
    PointRouteAMMapper pointRouteAMMapper;

    @PostMapping("/insertpoint")
    public Object insert(String pointWKT, Integer disasterid){
        Integer route_id = pointMapper.getNearestRouteId(pointWKT);
        double M = pointMapper.getPointM(pointMapper.selectRouteGeom(route_id), pointWKT);
        String pointonline = pointMapper.getPointonLine(pointMapper.selectRouteGeom(route_id), M);
        pointMapper.insertPointEvent(M, pointonline, disasterid, route_id);
        pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), pointMapper.selectRouteGeom(route_id), disasterid, route_id);
        return "success";
    }

    @GetMapping("/getpoint")
    public Object get(Integer id){
//        String geohash1 = GeoHash.geoHashStringWithCharacterPrecision(0,0,5);
//        System.out.println(geohash1);
//        GeoHash Geohash1 = GeoHash.fromGeohashString(geohash1);
//        System.out.println(Geohash1);
//
//        String geohash2 = GeoHash.geoHashStringWithCharacterPrecision(1,1,5);
//        System.out.println(geohash2);
//        GeoHash Geohash2 = GeoHash.fromGeohashString(geohash2);
//        System.out.println(Geohash2);
//
//        System.out.println(GeoHash.stepsBetween(Geohash1,Geohash2));
        PointEvent pointevent = pointMapper.selectPointEvent(id);
        return pointevent.toString();
    }

    @DeleteMapping("/deletepoint")
    public Object delete(Integer id){
        pointMapper.deleteById(id);
        return "success";
    }


}
