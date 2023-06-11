package whu.edu.cn.service;

import org.springframework.stereotype.Service;
import whu.edu.cn.entity.Disaster;
import whu.edu.cn.entity.LineEvent;
import whu.edu.cn.entity.PointEvent;
import whu.edu.cn.mapper.DisasterMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DisasterService {
    @Resource
    DisasterMapper disasterMapper;

    public List<Disaster> getAllDisaster(){
        List<Disaster> disasters = disasterMapper.getAllDisaster();
        return disasters;
    }
    Integer getPointEventNum(Integer id){
        Integer integer = disasterMapper.getPointEventNum(id);
        return integer;
    }
    List<PointEvent> getPointEventList(Integer id){
        List<PointEvent> pointEvents = disasterMapper.getPointEventList(id);
        return pointEvents;
    }
    List<String> getPointEventListGeom(Integer id){
        List<String> strings = disasterMapper.getPointEventListGeom(id);
        return strings;
    }
    Integer getLineEventNum(Integer id){
        Integer integer = disasterMapper.getLineEventNum(id);
        return integer;
    }
    List<LineEvent> getLineEventList(Integer id){
        List<LineEvent> lineEvents = disasterMapper.getLineEventList(id);
        return lineEvents;
    }
    List<String> getLineEventListGeom(Integer id){
        List<String> strings = disasterMapper.getLineEventListGeom(id);
        return strings;
    }
}
