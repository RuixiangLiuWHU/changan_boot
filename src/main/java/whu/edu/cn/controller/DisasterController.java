package whu.edu.cn.controller;

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
public class DisasterController {

    @Autowired
    DisasterMapper disasterMapper;

    @RequestMapping("/getdisaster")
    public Object get(Integer id){
        return disasterMapper.selectById(id);
    }

    @PostMapping("/insertdisaster")
    public Object insert(String name, String description, Date starttime, Date endtime){
        Disaster disaster = new Disaster();
        disaster.setName(name);
        disaster.setDescription(description);
        disaster.setStarttime(starttime);
        disaster.setEndtime(endtime);
        disasterMapper.insert(disaster);
        return "success";
    }
    @DeleteMapping("/deletedisaster")
    public Object delete(Integer id){
        disasterMapper.deleteById(id);
        return "success";
    }

    @RequestMapping("/getalldisaster")
    public List<Disaster> getalldisaster(){
        return disasterMapper.getAllDisaster();
    }

//    @RequestMapping("/update")
//    public String update(double m, Integer id){
//        userService.tranfor(m, id);
//        return "success";
//    }

    @RequestMapping("/getpointeventnum")
    public Integer getpointeventnum(Integer id){
        return disasterMapper.getPointEventNum(id);
    }

    @RequestMapping("/getpointeventlist")
    public List<PointEvent> getpointeventlist(Integer id){
        return disasterMapper.getPointEventList(id);
    }

    @RequestMapping("/getpointeventlistgeom")
    public List<String> getpointeventlistgeom(Integer id){
        return disasterMapper.getPointEventListGeom(id);
    }
//    @RequestMapping("/getpointeventlistgeometry")
//    public

    @RequestMapping("/getlineeventnum")
    public Integer getlineeventnum(Integer id){
        return disasterMapper.getLineEventNum(id);
    }

    @RequestMapping("/getlineeventlist")
    public List<LineEvent> getlineeventlist(Integer id){
        return disasterMapper.getLineEventList(id);
    }

    @RequestMapping("/getlineeventlistgeom")
    public List<String> getlineeventlistgeom(Integer id){
        return disasterMapper.getLineEventListGeom(id);
    }
}
