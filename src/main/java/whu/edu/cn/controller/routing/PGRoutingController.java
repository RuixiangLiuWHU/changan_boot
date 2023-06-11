package whu.edu.cn.controller.routing;

import ch.hsr.geohash.GeoHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import whu.edu.cn.entity.Route;
import whu.edu.cn.entity.routing.PGRouting;
import whu.edu.cn.mapper.DisasterMapper;
import whu.edu.cn.mapper.LineMapper;
import whu.edu.cn.mapper.routing.PGRoutingMapper;

import java.util.List;

import static java.lang.Math.sqrt;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PGRoutingController {
    @Autowired
    PGRoutingMapper pgRoutingMapper;

    @Autowired
    LineMapper lineMapper;

    @Autowired
    DisasterMapper disasterMapper;

    @GetMapping("/getpgrouting")
    public PGRouting get(Integer kind, double x1, double y1, double x2, double y2, double speed) {
        PGRouting pgRouting = new PGRouting();
        if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 1.349) {
            pgRoutingMapper.createview4(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 0.3597 && sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 1.349) {
            pgRoutingMapper.createview4(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 0.045 && sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 0.3597) {
            pgRoutingMapper.createview5(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 0.0113 && sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 0.045) {
            pgRoutingMapper.createview6(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 0.0015 && sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 0.022) {
            pgRoutingMapper.createview7(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 0.0015) {
            pgRoutingMapper.createview72(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        }
        return pgRouting;
    }

    @GetMapping("/getroute")
    public PGRouting get(Integer kind, double x1, double y1, double x2, double y2, double speed, Integer disasterid) {
        List<Integer> integerList = pgRoutingMapper.getRouteID(11);
        for (int i = 0; i < integerList.size(); i++) {
            pgRoutingMapper.updatecostorigin(integerList.get(i));
        }
        if (disasterid != 0) {
            List<Integer> integerList2 = pgRoutingMapper.getRouteID(disasterid);
            for (int i = 0; i < integerList2.size(); i++) {
                pgRoutingMapper.updatecost(10000000000000.0, integerList2.get(i));
            }
        }
        PGRouting pgRouting = new PGRouting();
        if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 1.349) {
            pgRoutingMapper.createview4(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 0.3597 && sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 1.349) {
            pgRoutingMapper.createview4(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 0.045 && sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 0.3597) {
            pgRoutingMapper.createview5(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 0.0113 && sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 0.045) {
            pgRoutingMapper.createview6(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 0.0015 && sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 0.022) {
            pgRoutingMapper.createview7(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        } else if (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 0.0015) {
            pgRoutingMapper.createview72(x1, y1, x2, y2);
            if (kind == 1) {
                pgRouting = pgRoutingMapper.getPGRoutingDistance(x1, y1, x2, y2, speed);
            } else if (kind == 2) {
                pgRouting = pgRoutingMapper.getPGRoutingTime(x1, y1, x2, y2, speed);
            } else if (kind == 3) {
                pgRouting = pgRoutingMapper.getPGRoutingPriority(x1, y1, x2, y2, speed);
            }
        }
        System.out.println("pgRouting.getGeom() = " + pgRouting.getGeom());
        Route routeStart = lineMapper.getNearestRouteId(x1, y1);
        String routeStartGeom = routeStart.getGeom();
        double startM = lineMapper.getLineMByXY(routeStartGeom, x1, y1);
        String startOnLine = lineMapper.getPointonLine(routeStartGeom, startM);
        System.out.println("startOnLine = " + startOnLine);
        if (lineMapper.intersects(pgRouting.getGeom(), startOnLine)) {
            String startGeom = lineMapper.getLineGeom(routeStartGeom, 0, startM);
            startGeom = startGeom.replace(")", ",").replace("LINESTRING(", "");
            if (pgRouting.getGeom().contains(startGeom)) {
                pgRouting.setGeom(pgRouting.getGeom().replace(startGeom, ""));
            }
            System.out.println("00");
        } else {
            String startGeom = lineMapper.getLineGeom(routeStartGeom, 0, startM);
            startGeom = lineMapper.getReverse(startGeom);
            startGeom = startGeom.replace(")", ",");
            pgRouting.setGeom(pgRouting.getGeom().replace("LINESTRING(", startGeom));
            System.out.println("01");
        }

        Route routeEnd = lineMapper.getNearestRouteId(x2, y2);
        String routeEndGeom = routeEnd.getGeom();
        double endM = lineMapper.getLineMByXY(routeEndGeom, x2, y2);
        String endOnLine = lineMapper.getPointonLine(routeEndGeom, endM);
        System.out.println("endOnLine = " + endOnLine);
        if (lineMapper.intersects(pgRouting.getGeom(), endOnLine)) {
            double M = lineMapper.getLineMByXY(routeEndGeom, x2, y2);
            String endGeom = lineMapper.getLineGeom(routeEndGeom, M, 1);
            endGeom = endGeom.replace(")", "").replace("LINESTRING(", ",");
            if (pgRouting.getGeom().contains(endGeom)) {
                pgRouting.setGeom(pgRouting.getGeom().replace(endGeom, "endGeom"));
            }
            System.out.println("10");
        } else {
            double M = lineMapper.getLineMByXY(routeEndGeom, x2, y2);
            String endGeom = lineMapper.getLineGeom(routeEndGeom, M, 1);
            endGeom = lineMapper.getReverse(endGeom);
            endGeom = endGeom.replace("LINESTRING(", ",");
            pgRouting.setGeom(pgRouting.getGeom().replace(")", endGeom));
            System.out.println("11");
        }

        return pgRouting;
    }

    @PostMapping("/updateroutecost")
    public Object updateroutecost(Integer disasterid) {
        if (disasterid != 0) {
            List<Integer> integerList = pgRoutingMapper.getRouteID(disasterid);
            for (int i = 0; i < integerList.size(); i++) {
                pgRoutingMapper.updatecost(10000000000000.0, integerList.get(i));
            }
        }
        return "success";
    }

    @PostMapping("/updateroutecostorigin")
    public Object updateroutecostorigin(Integer disasterid) {
        if (disasterid != 0) {
            List<Integer> integerList = pgRoutingMapper.getRouteID(disasterid);
            for (int i = 0; i < integerList.size(); i++) {
                pgRoutingMapper.updatecostorigin(integerList.get(i));
            }
        }
        return "success";
    }

    @PostMapping("/updategeohash7")
    public Object update7() {
        int routeNum = lineMapper.getRouteNum();
        for (int i = 1; i <= routeNum; i++) {
            double startx = pgRoutingMapper.getStartX(i);
            double starty = pgRoutingMapper.getStartY(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(starty, startx, 7);
            pgRoutingMapper.updateGeohash7(geohash, i);
        }
        return "success";
    }

    @PostMapping("/updategeohash6")
    public Object update6() {
        int routeNum = lineMapper.getRouteNum();
        for (int i = 1; i <= routeNum; i++) {
            double startx = pgRoutingMapper.getStartX(i);
            double starty = pgRoutingMapper.getStartY(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(starty, startx, 6);
            pgRoutingMapper.updateGeohash6(geohash, i);
        }
        return "success";
    }

    @PostMapping("/updategeohash5")
    public Object update5() {
        int routeNum = lineMapper.getRouteNum();
        for (int i = 1; i <= routeNum; i++) {
            double startx = pgRoutingMapper.getStartX(i);
            double starty = pgRoutingMapper.getStartY(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(starty, startx, 5);
            pgRoutingMapper.updateGeohash5(geohash, i);
        }
        return "success";
    }

    @PostMapping("/updategeohash4")
    public Object update4() {
        int routeNum = lineMapper.getRouteNum();
        for (int i = 1; i <= routeNum; i++) {
            double startx = pgRoutingMapper.getStartX(i);
            double starty = pgRoutingMapper.getStartY(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(starty, startx, 4);
            pgRoutingMapper.updateGeohash4(geohash, i);
        }
        return "success";
    }

    @PostMapping("/updategeohash3")
    public Object update3() {
        int routeNum = lineMapper.getRouteNum();
        for (int i = 1; i <= routeNum; i++) {
            double startx = pgRoutingMapper.getStartX(i);
            double starty = pgRoutingMapper.getStartY(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(starty, startx, 3);
            pgRoutingMapper.updateGeohash3(geohash, i);
        }
        return "success";
    }
}
