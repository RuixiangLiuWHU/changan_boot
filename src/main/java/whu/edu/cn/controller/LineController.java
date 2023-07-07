package whu.edu.cn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whu.edu.cn.entity.Levenshtein;
import whu.edu.cn.entity.Route;
import whu.edu.cn.mapper.LineMapper;

import java.util.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "线事件管理接口")
public class LineController {
    @Autowired
    LineMapper lineMapper;

    @ApiOperation("通过坐标获取距离最近的线事件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "X坐标", required = true),
            @ApiImplicitParam(name = "y", value = "Y坐标", required = true)
    })
    @GetMapping("/getlineeventrouteid")
    public Route getlineeventrouteid(double x, double y) {
        return lineMapper.getNearestRouteId(x, y);
    }

    @ApiOperation("插入线事件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pointWKTStart", value = "起点坐标（WKT）", required = true),
            @ApiImplicitParam(name = "pointWKTEnd", value = "终点坐标（WKT）", required = true),
            @ApiImplicitParam(name = "disasterid", value = "灾害ID", required = true),
            @ApiImplicitParam(name = "routeid", value = "道路ID", required = true)
    })
    @PostMapping("/insertline")
    public Object insert(String pointWKTStart, String pointWKTEnd, Integer disasterid, Integer routeid) {
        String routegeom = lineMapper.getRouteGeom(routeid);
        double fromm = lineMapper.getLineM(routegeom, pointWKTStart);
        double tom = lineMapper.getLineM(routegeom, pointWKTEnd);
        if (fromm > tom) {
            String linegeom = lineMapper.getLineGeom(routegeom, tom, fromm);
            lineMapper.insertLine(tom, fromm, linegeom, disasterid, routeid);
        } else {
            String linegeom = lineMapper.getLineGeom(routegeom, fromm, tom);
            lineMapper.insertLine(fromm, tom, linegeom, disasterid, routeid);
        }
        return "success";
    }

    @ApiOperation("按相似度排序最相似的道路名称")
    @ApiImplicitParam(name = "str", value = "希望比较的道路名称", required = true)
    @GetMapping("/getlevenshtein")
    public List<String> getLevenshtein(String str) {
        //计算两个字符串的长度。
        List<Levenshtein> levenshteinList = new ArrayList<>();
        Set<String> stringSet = new HashSet<>();
        int len1 = str.length();
        List<String> stringList = lineMapper.getRouteName();
        for (int le = 0; le < stringList.size(); le++) {
            String str2 = stringList.get(le);
            int len2 = str2.length();
            //建立上面说的数组，比字符长度大一个空间
            int[][] dif = new int[len1 + 1][len2 + 1];
            //赋初值，步骤B。
            for (int a = 0; a <= len1; a++) {
                dif[a][0] = a;
            }
            for (int a = 0; a <= len2; a++) {
                dif[0][a] = a;
            }
            //计算两个字符是否一样，计算左上的值
            int temp;
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (str.charAt(i - 1) == str2.charAt(j - 1)) {
                        temp = 0;
                    } else {
                        temp = 1;
                    }
                    //取三个值中最小的
                    dif[i][j] = Math.min(Math.min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1),
                            dif[i - 1][j] + 1);
                }
            }
            double similarity = 1 - (double) dif[len1][len2] / Math.max(str.length(), str2.length());
            if(stringSet.add(str2)) {
                levenshteinList.add(new Levenshtein(str2, similarity));
            }
        }
        Collections.sort(levenshteinList);
        List<String> out = new ArrayList<>();
        for (int ii = 0; ii < 100; ii++) {
            if(levenshteinList.get(levenshteinList.size()-1-ii).getSimilarity()>0) {
                out.add(levenshteinList.get(levenshteinList.size() - 1 - ii).getName());
            }
        }
        return out;
    }

    @ApiOperation("通过名称获取道路")
    @ApiImplicitParam(name = "name", value = "道路名称", required = true)
    @GetMapping("/getroutebyname")
    List<Route> getroutebyname(String name){
        List<Route> routeList = lineMapper.getRouteByName(name);
        return routeList;
    }
}
