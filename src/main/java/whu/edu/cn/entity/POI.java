package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;

@TableName(value = "poi", autoResultMap = true)
public class POI {
    private Integer gid;
    private String name;
    private String address;
    private double lng;
    private double lat;
    private String industryclass;
    private String industrysubclass;
    private String geom;
    private String geohash;
    private Integer fclass;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getIndustryclass() {
        return industryclass;
    }

    public void setIndustryclass(String industryclass) {
        this.industryclass = industryclass;
    }

    public String getIndustrysubclass() {
        return industrysubclass;
    }

    public void setIndustrysubclass(String industrysubclass) {
        this.industrysubclass = industrysubclass;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public Integer getFclass() {
        return fclass;
    }

    public void setFclass(Integer fclass) {
        this.fclass = fclass;
    }

    @Override
    public String toString() {
        return "POI{" +
                "gid=" + gid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", industryclass='" + industryclass + '\'' +
                ", industrysubclass='" + industrysubclass + '\'' +
                ", geom='" + geom + '\'' +
                ", geohash='" + geohash + '\'' +
                ", fclass=" + fclass +
                '}';
    }
}
