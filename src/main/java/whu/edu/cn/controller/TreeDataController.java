package whu.edu.cn.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import whu.edu.cn.entity.TreeData;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TreeDataController {
    @GetMapping("/inittreedata")
    public Object inittreedata() {
//        TreeData  allLayers= new TreeData();
//        allLayers.setName("所有图层");
//        allLayers.setKey("0-0");
//        TreeData.TreeChild basicLayers = allLayers.new TreeChild();
//        List<String>
//        basicLayers.setName("基础图层");
//        basicLayers.setKey("0-0-0");
//        allLayers.setChild(basicLayers);
//        String jsonOutput = JSON.toJSONString(TreeDatas);
//
//        return jsonOutput;
        return "success";
    }
}
