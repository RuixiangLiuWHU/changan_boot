package whu.edu.cn.service;

import org.springframework.stereotype.Service;
import whu.edu.cn.entity.POI;
import whu.edu.cn.mapper.POIMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class POIService {
    @Resource
    POIMapper poiMapper;
    List<POI> getSelectPOIAffected0720(Integer[] ids){
        List<POI> poiList = poiMapper.getSelectPOIAffected0720(ids);
        return poiList;
    }
    List<POI> getSelectPOIAffected0722(Integer[] ids){
        List<POI> poiList = poiMapper.getSelectPOIAffected0720(ids);
        return poiList;
    }
    Integer getSelectPOINum(Integer[] ids) {
        Integer integer = poiMapper.getSelectPOINum(ids);
        return integer;
    }
    List<POI> getSelectPOI(Integer[] ids) {
        List<POI> poiList = poiMapper.getSelectPOI(ids);
        return poiList;
    }
    List<String> getPOIFlood0720(String geohash) {
        List<String> stringList = getPOIFlood0720(geohash);
        return stringList;
    }
    List<String> getPOIFlood0722(String geohash) {
        List<String> stringList = getPOIFlood0722(geohash);
        return stringList;
    }
    Boolean getIntersects(String geom1, String geom2) {
        Boolean b = poiMapper.getIntersects(geom1, geom2);
        return b;
    }
    Integer getEduPOINum() {
        Integer integer = poiMapper.getEduPOINum();
        return integer;
    }
    POI getEduPOI(Integer gid) {
        POI poi = poiMapper.getEduPOI(gid);
        return poi;
    }
    Integer getTransPOINum() {
        Integer integer = poiMapper.getTransPOINum();
        return integer;
    }
    POI getTransPOI(Integer gid) {
        POI poi = poiMapper.getTransPOI(gid);
        return poi;
    }
    Integer getHotelPOINum() {
        Integer integer = poiMapper.getHotelPOINum();
        return integer;
    }
    POI getHotelPOI(Integer gid) {
        POI poi = poiMapper.getHotelPOI(gid);
        return poi;
    }
    Integer getViewPOINum() {
        Integer integer = poiMapper.getViewPOINum();
        return integer;
    }
    POI getViewPOI(Integer gid) {
        POI poi = poiMapper.getViewPOI(gid);
        return poi;
    }
    Integer getMedicalPOINum() {
        Integer integer = poiMapper.getMedicalPOINum();
        return integer;
    }
    POI getMedicalPOI(Integer gid) {
        POI poi = poiMapper.getMedicalPOI(gid);
        return poi;
    }
    Integer getAdmPOINum() {
        Integer integer = poiMapper.getAdmPOINum();
        return integer;
    }
    POI getAdmPOI(Integer gid) {
        POI poi = poiMapper.getAdmPOI(gid);
        return poi;
    }
    List<String> getPOIName() {
        List<String> stringList = poiMapper.getPOIName();
        return stringList;
    }
    POI getPOIByName(String name){
        POI poi = poiMapper.getPOIByName(name);
        return poi;
    }
}
