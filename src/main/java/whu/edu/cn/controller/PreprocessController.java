package whu.edu.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import whu.edu.cn.entity.Preprocess;
import whu.edu.cn.mapper.PreprocessMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@ApiIgnore
public class PreprocessController {
    @Autowired
    PreprocessMapper preprocessMapper;

    @PostMapping("/updatesichuan")
    public Object updateSichuan() {
        for (int i = 1604029; i <= 2430412; i++) {
            preprocessMapper.updateGidOfSichuan(i);
        }
        return "success";
    }

    @PostMapping("/getsourceandtarget")
    public Object getSourceAndTarget() {
        Long time1 = System.currentTimeMillis();
        List<Map<Long, Long>> listsMap = preprocessMapper.getGid();
        Map<Long, Long> map = new HashMap<>();
        for (Map<Long, Long> longLongMap : listsMap) {
            map.put(longLongMap.get("bid"), longLongMap.get("aid"));
        }
        List<Map<Long, Long>> listsMapId = preprocessMapper.getId();
        Map<Long, Long> mapId = new HashMap<>();
        for (Map<Long, Long> longLongMap : listsMapId) {
            mapId.put(longLongMap.get("bid"), longLongMap.get("aid"));
        }
        List<Preprocess> preprocessList = preprocessMapper.getAllSAT();
        Long time2 = System.currentTimeMillis();
        System.out.println("(time2-time1) = " + (time2 - time1));
        //812985
        for (int i = 230000; i <= 826384; i++) {
            Preprocess sat = preprocessList.get(i - 1);
            Long newSource = mapId.get(sat.getSource());
            Long newTarget = mapId.get(sat.getTarget());
            Long newId = map.get((long) i);
            preprocessMapper.updateSAT(newId, newSource, newTarget);
            if (i % 10000 == 0) {
                System.out.println("i = " + i);
            }
        }
        return "success";
    }
}
