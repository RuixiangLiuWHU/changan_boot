package whu.edu.cn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whu.edu.cn.entity.PointEvent;
import whu.edu.cn.mapper.PointMapper;
import whu.edu.cn.mapper.aftermatching.PointRouteAMMapper;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "点事件管理接口")
public class PointController {

    @Autowired
    PointMapper pointMapper;

    @Autowired
    PointRouteAMMapper pointRouteAMMapper;

    @ApiOperation("插入点事件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pointWKT", value = "点坐标（WKT）", required = true),
            @ApiImplicitParam(name = "disasterid", value = "灾害ID", required = true)
    })
    @PostMapping("/insertpoint")
    public Object insert(String pointWKT, Integer disasterid) {
        Integer route_id = pointMapper.getNearestRouteId(pointWKT);
        double M = pointMapper.getPointM(pointMapper.selectRouteGeom(route_id), pointWKT);
        String pointonline = pointMapper.getPointonLine(pointMapper.selectRouteGeom(route_id), M);
        pointMapper.insertPointEvent(M, pointonline, disasterid, route_id);
        pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), pointMapper.selectRouteGeom(route_id), disasterid, route_id);
        return "success";
    }

    @ApiOperation("通过ID获取点事件")
    @ApiImplicitParam(name = "id", value = "点事件ID", required = true)
    @GetMapping("/getpoint")
    public Object get(Integer id) {
        PointEvent pointevent = pointMapper.selectPointEvent(id);
        return pointevent.toString();
    }

    @ApiOperation("通过ID删除点事件")
    @ApiImplicitParam(name = "id", value = "点事件ID", required = true)
    @DeleteMapping("/deletepoint")
    public Object delete(Integer id) {
        pointMapper.deleteById(id);
        return "success";
    }


}
