package whu.edu.cn.controller;

import ch.hsr.geohash.GeoHash;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import whu.edu.cn.entity.Levenshtein;
import whu.edu.cn.entity.POI;
import whu.edu.cn.mapper.POIMapper;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "POI管理接口")
public class POIController {
    @Autowired
    POIMapper poiMapper;

    @ApiOperation("按相似度排序最相似的POI名称")
    @ApiImplicitParam(name = "str", value = "希望比较的POI名称", required = true)
    @GetMapping("/getlevenshteinpoi")
    public List<String> getLevenshteinPOI(String str) {
        //计算两个字符串的长度。
        List<Levenshtein> levenshteinList = new ArrayList<>();
        Set<String> stringSet = new HashSet<>();
        int len1 = str.length();
        List<String> stringList = poiMapper.getPOIName();
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
            if (stringSet.add(str2)) {
                levenshteinList.add(new Levenshtein(str2, similarity));
            }
        }
        Collections.sort(levenshteinList);
        List<String> out = new ArrayList<>();
        for (int ii = 0; ii < 100; ii++) {
            if (levenshteinList.get(levenshteinList.size() - 1 - ii).getSimilarity() > 0) {
                out.add(levenshteinList.get(levenshteinList.size() - 1 - ii).getName());
            }
        }
        return out;
    }

    @ApiOperation("通过名称获取POI")
    @ApiImplicitParam(name = "name", value = "POI名称", required = true)
    @GetMapping("/getpoibyname")
    POI getpoibyname(String name) {
        POI poi = poiMapper.getPOIByName(name);
        return poi;
    }

    @ApiOperation("获取受影响的POI")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pois", value = "POI种类", required = true),
            @ApiImplicitParam(name = "disasterid", value = "灾害ID", required = true)
    })
    @GetMapping("/getpoiaffected")
    public List<POI> getpoiaffected(Integer[] pois, Integer disasterid) {
        List<POI> poiList = new ArrayList<>();
        if (disasterid == 2) {
            poiList = poiMapper.getSelectPOIAffected0720(pois);
        } else if (disasterid == 3) {
            poiList = poiMapper.getSelectPOIAffected0722(pois);
        }
        return poiList;
    }

    @ApiOperation(value = "内部处理POI", hidden = true)
    @PostMapping("/processpoi")
    public Object processpoi(Integer[] pois) {
        poiMapper.clearPOIAffected0720();
        poiMapper.clearPOIAffected0722();
        Integer num0720 = 1;
        Integer num0722 = 1;
        Integer selectPOINum = poiMapper.getSelectPOINum(pois);
        List<POI> poiList = poiMapper.getSelectPOI(pois);
        for (int i = 0; i < selectPOINum; i++) {
            String geohash = poiList.get(i).getGeohash();
            List<String> stringList0720 = poiMapper.getPOIFlood0720(geohash);
            List<String> stringList0722 = poiMapper.getPOIFlood0722(geohash);
            for (int j = 0; j < stringList0720.size(); j++) {
                if (poiMapper.getIntersects(stringList0720.get(j), poiList.get(i).getGeom())) {
                    poiMapper.insertPOIAffected0720(num0720, poiList.get(i).getName(), poiList.get(i).getAddress(),
                            poiList.get(i).getLng(), poiList.get(i).getLat(), poiList.get(i).getIndustryclass(),
                            poiList.get(i).getIndustrysubclass(), poiList.get(i).getGeom(), poiList.get(i).getGeohash(),
                            poiList.get(i).getFclass());
                    num0720++;
                    break;
                }
            }
            for (int j = 0; j < stringList0722.size(); j++) {
                if (poiMapper.getIntersects(stringList0722.get(j), poiList.get(i).getGeom())) {
                    poiMapper.insertPOIAffected0722(num0722, poiList.get(i).getName(), poiList.get(i).getAddress(),
                            poiList.get(i).getLng(), poiList.get(i).getLat(), poiList.get(i).getIndustryclass(),
                            poiList.get(i).getIndustrysubclass(), poiList.get(i).getGeom(), poiList.get(i).getGeohash(),
                            poiList.get(i).getFclass());
                    num0722++;
                    break;
                }
            }
        }
        return "success";
    }

    @ApiOperation(value = "内部插入POI", hidden = true)
    @PostMapping("/insertpoi")
    public Object insert() {
        for (int i = 1; i <= poiMapper.getEduPOINum(); i++) {
            POI poi = poiMapper.getEduPOI(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(poi.getLat(), poi.getLng(), 7);
            poiMapper.insertPOI(poi.getGid(), poi.getName(), poi.getAddress(), poi.getLng(), poi.getLat(),
                    poi.getIndustryclass(), poi.getIndustrysubclass(), poi.getGeom(), geohash, poi.getFclass());
        }
        for (int i = 1; i <= poiMapper.getTransPOINum(); i++) {
            POI poi = poiMapper.getTransPOI(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(poi.getLat(), poi.getLng(), 7);
            poiMapper.insertPOI(poi.getGid() + poiMapper.getEduPOINum(), poi.getName(), poi.getAddress(), poi.getLng(), poi.getLat(),
                    poi.getIndustryclass(), poi.getIndustrysubclass(), poi.getGeom(), geohash, poi.getFclass());
        }
        for (int i = 1; i <= poiMapper.getHotelPOINum(); i++) {
            POI poi = poiMapper.getHotelPOI(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(poi.getLat(), poi.getLng(), 7);
            poiMapper.insertPOI(poi.getGid() + poiMapper.getEduPOINum() + poiMapper.getTransPOINum(), poi.getName(), poi.getAddress(), poi.getLng(), poi.getLat(),
                    poi.getIndustryclass(), poi.getIndustrysubclass(), poi.getGeom(), geohash, poi.getFclass());
        }
        for (int i = 1; i <= poiMapper.getViewPOINum(); i++) {
            POI poi = poiMapper.getViewPOI(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(poi.getLat(), poi.getLng(), 7);
            poiMapper.insertPOI(poi.getGid() + poiMapper.getEduPOINum() + poiMapper.getTransPOINum() + poiMapper.getHotelPOINum(), poi.getName(), poi.getAddress(), poi.getLng(), poi.getLat(),
                    poi.getIndustryclass(), poi.getIndustrysubclass(), poi.getGeom(), geohash, poi.getFclass());
        }
        for (int i = 1; i <= poiMapper.getMedicalPOINum(); i++) {
            POI poi = poiMapper.getMedicalPOI(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(poi.getLat(), poi.getLng(), 7);
            poiMapper.insertPOI(poi.getGid() + poiMapper.getEduPOINum() + poiMapper.getTransPOINum() + poiMapper.getHotelPOINum() + poiMapper.getViewPOINum(), poi.getName(), poi.getAddress(), poi.getLng(), poi.getLat(),
                    poi.getIndustryclass(), poi.getIndustrysubclass(), poi.getGeom(), geohash, poi.getFclass());
        }
        for (int i = 1; i <= poiMapper.getAdmPOINum(); i++) {
            POI poi = poiMapper.getAdmPOI(i);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(poi.getLat(), poi.getLng(), 7);
            poiMapper.insertPOI(poi.getGid() + poiMapper.getEduPOINum() + poiMapper.getTransPOINum() + poiMapper.getHotelPOINum() + poiMapper.getViewPOINum() + poiMapper.getMedicalPOINum(), poi.getName(), poi.getAddress(), poi.getLng(), poi.getLat(),
                    poi.getIndustryclass(), poi.getIndustrysubclass(), poi.getGeom(), geohash, poi.getFclass());
        }
        return "success";
    }
}
