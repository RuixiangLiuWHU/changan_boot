package whu.edu.cn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import whu.edu.cn.entity.Route;
import whu.edu.cn.entity.Vertices;
import whu.edu.cn.mapper.RoadMapper;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "道路管理接口")
public class RoadController {
    @Autowired
    RoadMapper roadMapper;

    @ApiOperation("获取所有的道路")
    @GetMapping("/getallroads")
    public Object getAllRoads() {
        List<Route> routes = roadMapper.getAllRoads();
        return routes;
    }

    @ApiOperation("获取所有的道路节点")
    @GetMapping("/getallvertices")
    public Object getAllVertices() {
        List<Vertices> vertices = roadMapper.getAllVertices();
        return vertices;
    }
}
