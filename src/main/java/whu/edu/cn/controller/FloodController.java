package whu.edu.cn.controller;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.util.BoundingBoxGeoHashIterator;
import ch.hsr.geohash.util.TwoGeoHashBoundingBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import whu.edu.cn.entity.Route;
import whu.edu.cn.mapper.FloodMapper;
import whu.edu.cn.mapper.LineMapper;
import whu.edu.cn.mapper.PointMapper;
import whu.edu.cn.mapper.aftermatching.PointRouteAMMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@ApiIgnore
public class FloodController {
    @Autowired
    FloodMapper floodMapper;
    @Autowired
    PointMapper pointMapper;
    @Autowired
    LineMapper lineMapper;
    @Autowired
    PointRouteAMMapper pointRouteAMMapper;

    @PostMapping ("/insertflood")
    public Object insertflood() {
        for (int i = 1; i <= 18927; i++) {//18927//12525
            List<Integer> routeids = new ArrayList<>();
            String[] floodstrings = (String[]) floodMapper.getFloodGeohash(i);//flood表中第i行数据包含的geohash数组
            for (int j = 0; j < floodstrings.length; j++) {
                String string = floodstrings[j];//第j个geohash值
                List<Route> routes = floodMapper.getGeoRoute(string);//获取此geohash值重叠的路，但是对于一个flood来说，不能有冗余
                for (int k = 0; k < routes.size(); k++) {
                    int flag = 0;
                    int y = 0;
                    for (int x = 0; x < routeids.size(); x++) {
                        if (routeids.get(x).equals(routes.get(k).getGid())) {
                            y = y + 1;
                        }
                    }
                    if (y == 0) {
                        routeids.add(routes.get(k).getGid());
                        flag = 1;
                    }
                    if (flag == 1) {
                        if (floodMapper.getIntersects(floodMapper.getFlood(i), routes.get(k).getGeom())) {
                            String routegeom = routes.get(k).getGeom();
                            String common = floodMapper.getIntersection(floodMapper.getFlood(i), routegeom);
                            System.out.println("common = " + common);
                            String[] dumpcommon = floodMapper.dumpMultiLineString(common);
                            for (int l = 0; l < dumpcommon.length; l++) {
                                double d = floodMapper.getLength(dumpcommon[l]);
                                System.out.println("d = " + d);
                                if (d > 0.0002) {
                                    String start = floodMapper.getStartPoint(dumpcommon[l]);
                                    System.out.println("start = " + start);
                                    String end = floodMapper.getEndPoint(dumpcommon[l]);
                                    System.out.println("end = " + end);
                                    double fromm = lineMapper.getLineM(routegeom, start);
                                    System.out.println("fromm = " + fromm);
                                    double tom = lineMapper.getLineM(routegeom, end);
                                    System.out.println("tom = " + tom);
                                    if (fromm > tom) {
                                        lineMapper.insertLine(tom, fromm, dumpcommon[l], 2, routes.get(k).getGid());
                                    } else {
                                        lineMapper.insertLine(fromm, tom, dumpcommon[l], 2, routes.get(k).getGid());
                                    }
                                } else if (d <= 0.0002) {
                                    String pointcentroid = floodMapper.getCentroid(dumpcommon[l]);
                                    System.out.println("pointcentroid = " + pointcentroid);
                                    double m = pointMapper.getPointM(routegeom, pointcentroid);
                                    System.out.println("m = " + m);
                                    pointMapper.insertPointEvent(m, pointcentroid, 2, routes.get(k).getGid());
                                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routes.get(k).getGeom(), 2, routes.get(k).getGid());
                                }
                            }
                        }
                    }
                }
            }
        }
        return "success";
    }

    @PostMapping("/updatefloodgeohash")
    public Object update() {
        for (int i = 1; i <= 12525; i++) {//18927
            String flood = floodMapper.getFlood(i);
            String floodMBR = floodMapper.getMBR(flood);
            double MBRLeftDownX = floodMapper.getMBRLeftDownX(floodMBR);
            double MBRLeftDownY = floodMapper.getMBRLeftDownY(floodMBR);
            double MBRRightUpX = floodMapper.getMBRRightUpX(floodMBR);
            double MBRRightUpY = floodMapper.getMBRRightUpY(floodMBR);
            GeoHash southWestCorner = GeoHash.withCharacterPrecision(MBRLeftDownY, MBRLeftDownX, 7);
            GeoHash northEastCorner = GeoHash.withCharacterPrecision(MBRRightUpY, MBRRightUpX, 7);
            TwoGeoHashBoundingBox twoGeoHashBoundingBox = new TwoGeoHashBoundingBox(southWestCorner, northEastCorner);
            BoundingBoxGeoHashIterator iterator = new BoundingBoxGeoHashIterator(twoGeoHashBoundingBox);
            List<String> floodgeohashlist = new ArrayList<>();
            GeoHash geoHash;
            while (iterator.hasNext()) {
                geoHash = iterator.next();
                floodgeohashlist.add(geoHash.toBase32());
            }
            String[] floodgeohashstring = new String[floodgeohashlist.size()];
            floodgeohashlist.toArray(floodgeohashstring);
            floodMapper.updateGeohash(floodgeohashstring, i);
        }
        return "success";
    }

    @PostMapping("/updateroutegeohash")
    public Object updateRoute() {
        int routeNum = lineMapper.getRouteNum();
        for (int i = 1; i <= routeNum; i++) {
            String route = floodMapper.getRoute(i).getGeom();
            String routeMBR = floodMapper.getMBR(route);
            double MBRLeftDownX = floodMapper.getMBRLeftDownX(routeMBR);
            double MBRLeftDownY = floodMapper.getMBRLeftDownY(routeMBR);
            double MBRRightUpX = floodMapper.getMBRRightUpX(routeMBR);
            double MBRRightUpY = floodMapper.getMBRRightUpY(routeMBR);
            GeoHash southWestCorner = GeoHash.withCharacterPrecision(MBRLeftDownY, MBRLeftDownX, 7);
            GeoHash northEastCorner = GeoHash.withCharacterPrecision(MBRRightUpY, MBRRightUpX, 7);
            TwoGeoHashBoundingBox twoGeoHashBoundingBox = new TwoGeoHashBoundingBox(southWestCorner, northEastCorner);
            BoundingBoxGeoHashIterator iterator = new BoundingBoxGeoHashIterator(twoGeoHashBoundingBox);
            List<String> routegeohashlist = new ArrayList<>();
            GeoHash geoHash;
            while (iterator.hasNext()) {
                geoHash = iterator.next();
                routegeohashlist.add(geoHash.toBase32());
            }
            //System.out.println("routegeohashlist = " + routegeohashlist);
            //System.out.println("routegeohashlist.size() = " + routegeohashlist.size());
            String[] routegeohashstring = new String[routegeohashlist.size()];
            routegeohashlist.toArray(routegeohashstring);
            //System.out.println("routegeohashstring.length = " + routegeohashstring.length);
            floodMapper.updateRouteGeohash(routegeohashstring, i);
        }
        return "success";
    }
}
