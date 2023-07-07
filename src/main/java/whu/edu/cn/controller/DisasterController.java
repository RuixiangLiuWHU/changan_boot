package whu.edu.cn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whu.edu.cn.entity.Disaster;
import whu.edu.cn.entity.LineEvent;
import whu.edu.cn.entity.PointEvent;
import whu.edu.cn.mapper.DisasterMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "灾害事件管理接口")
public class DisasterController {

    @Autowired
    DisasterMapper disasterMapper;

    @ApiOperation("通过ID获取灾害事件")
    @ApiImplicitParam(name = "id", value = "灾害ID", required = true)
    @GetMapping("/getdisaster")
    public Object get(Integer id) {
        return disasterMapper.selectById(id);
    }

    @ApiOperation("插入灾害事件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "灾害名称", required = true),
            @ApiImplicitParam(name = "description", value = "灾害描述", required = true),
            @ApiImplicitParam(name = "starttime", value = "灾害开始时间", required = true),
            @ApiImplicitParam(name = "endtime", value = "灾害结束时间", required = true)
    })
    @PostMapping("/insertdisaster")
    public Object insert(String name, String description, Date starttime, Date endtime) {
        Disaster disaster = new Disaster();
        disaster.setName(name);
        disaster.setDescription(description);
        disaster.setStarttime(starttime);
        disaster.setEndtime(endtime);
        disasterMapper.insert(disaster);
        return "success";
    }

    @ApiOperation("通过ID删除灾害事件")
    @ApiImplicitParam(name = "id", value = "灾害ID", required = true)
    @DeleteMapping("/deletedisaster")
    public Object delete(Integer id) {
        disasterMapper.deleteById(id);
        return "success";
    }

    @ApiOperation("获取所有的灾害事件")
    @GetMapping("/getalldisaster")
    public List<Disaster> getalldisaster() {
        return disasterMapper.getAllDisaster();
    }

//    @GetMapping("/update")
//    public String update(double m, Integer id){
//        userService.tranfor(m, id);
//        return "success";
//    }

    @ApiOperation("通过ID获取灾害事件中的点事件个数")
    @ApiImplicitParam(name = "id", value = "灾害ID", required = true)
    @GetMapping("/getpointeventnum")
    public Integer getpointeventnum(Integer id) {
        return disasterMapper.getPointEventNum(id);
    }

    @ApiOperation("通过ID获取灾害事件中的点事件列表")
    @ApiImplicitParam(name = "id", value = "灾害ID", required = true)
    @GetMapping("/getpointeventlist")
    public List<PointEvent> getpointeventlist(Integer id) {
        return disasterMapper.getPointEventList(id);
    }

    @ApiOperation("通过ID获取灾害事件中的点事件几何")
    @ApiImplicitParam(name = "id", value = "灾害ID", required = true)
    @GetMapping("/getpointeventlistgeom")
    public List<String> getpointeventlistgeom(Integer id) {
        return disasterMapper.getPointEventListGeom(id);
    }

    @ApiOperation("通过ID获取灾害事件中的线事件个数")
    @ApiImplicitParam(name = "id", value = "灾害ID", required = true)
    @GetMapping("/getlineeventnum")
    public Integer getlineeventnum(Integer id) {
        return disasterMapper.getLineEventNum(id);
    }

    @ApiOperation("通过ID获取灾害事件中的线事件列表")
    @ApiImplicitParam(name = "id", value = "灾害ID", required = true)
    @GetMapping("/getlineeventlist")
    public List<LineEvent> getlineeventlist(Integer id) {
        return disasterMapper.getLineEventList(id);
    }

    @ApiOperation("通过ID获取灾害事件中的线事件几何")
    @ApiImplicitParam(name = "id", value = "灾害ID", required = true)
    @GetMapping("/getlineeventlistgeom")
    public List<String> getlineeventlistgeom(Integer id) {
        return disasterMapper.getLineEventListGeom(id);
    }
}
