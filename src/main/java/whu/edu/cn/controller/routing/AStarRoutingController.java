package whu.edu.cn.controller.routing;

import ch.hsr.geohash.GeoHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whu.edu.cn.entity.routing.PGRouting;
import whu.edu.cn.mapper.routing.AStarRoutingSeparateMapper;

import java.util.*;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AStarRoutingController {
    @Autowired
    AStarRoutingSeparateMapper aStarRoutingSeparateMapper;

    @PostMapping("/updateastarrouting")
    public Object update(double x1, double y1, double x2, double y2){
        aStarRoutingSeparateMapper.clearAStarRoutingSeparate();
        Integer start = aStarRoutingSeparateMapper.getStartOrEnd(x1, y1);
        Integer end = aStarRoutingSeparateMapper.getStartOrEnd(x2, y2);
        double startlat = aStarRoutingSeparateMapper.getlat(start);
        double startlon = aStarRoutingSeparateMapper.getlon(start);
        GeoHash startgeohash = GeoHash.withCharacterPrecision(startlat, startlon,12);
        double endlat = aStarRoutingSeparateMapper.getlat(end);
        double endlon = aStarRoutingSeparateMapper.getlon(end);
        GeoHash endgeohash = GeoHash.withCharacterPrecision(endlat, endlon,12);
        List<Integer> integers = aStarRoutingSeparateMapper.getTarget(start);

//        long min = 9223372036854775807L;
        double min = 1000000000;
        List<Integer> closelist = new ArrayList<>();
        List<Integer> steplist = new ArrayList<>();
        List<Integer> startlist = new ArrayList<>();
        List<Integer> targetlist = new ArrayList<>();
        Integer target = 0;
        while(start != end) {
            for (int i = 0; i < integers.size(); i++) {

                //循环寻找最短的下一个点以及将下一个点当作新的起点
//                long between1 = GeoHash.stepsBetween(startgeohash,GeoHash.withCharacterPrecision(aStarRoutingSeparateMapper.getlat(integers.get(i)),aStarRoutingSeparateMapper.getlon(integers.get(i)),12));
//                long between2 = GeoHash.stepsBetween(endgeohash,GeoHash.withCharacterPrecision(aStarRoutingSeparateMapper.getlat(integers.get(i)),aStarRoutingSeparateMapper.getlon(integers.get(i)),12));
//                long between = between1 + between2;
                if (!closelist.contains(integers.get(i))) {
                    double between1 = Math.abs(startlat - aStarRoutingSeparateMapper.getlat(integers.get(i))) + Math.abs(startlon - aStarRoutingSeparateMapper.getlon(integers.get(i)));
                    double between2 = Math.abs(endlat - aStarRoutingSeparateMapper.getlat(integers.get(i))) + Math.abs(endlon - aStarRoutingSeparateMapper.getlon(integers.get(i)));
                    double between = between1 + between2;
                    if (between < min) {
                        min = between;
                        target = integers.get(i);
                    }
                }
                closelist.add(integers.get(i));
            }
            System.out.println("start = " + start);
            System.out.println("target = " + target);
            if(start!=target) {
                aStarRoutingSeparateMapper.insertAStarRoutingSeparate(start, target, aStarRoutingSeparateMapper.getAStarRoutingSeparateGeom(start, target));
                start = target;
                steplist.add(start);
                integers = aStarRoutingSeparateMapper.getTarget(start);
                min = 9223372036854775807L;
            }
            else if(start==target){
                steplist.remove(start);
                start = steplist.get(steplist.size()-1);
                integers = aStarRoutingSeparateMapper.getTarget(start);
                System.out.println("integers=" + integers);
                for(int i=0;i<integers.size();i++) {
                    closelist.remove(integers.get(i));
                }
                closelist.add(target);
                System.out.println("closelist=" + closelist);
            }
        }
        return "success";
    }
}
