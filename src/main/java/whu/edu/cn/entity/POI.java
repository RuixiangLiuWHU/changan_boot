package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

@ApiModel(description = "POI表的实体对象")
@TableName(value = "poi", autoResultMap = true)
public class POI {
    @ApiModelProperty(value = "POI编号")
    private Integer gid;
    @ApiModelProperty(value = "POI名称")
    private String name;
    @ApiModelProperty(value = "POI地址")
    private String address;
    @ApiModelProperty(value = "POI经度")
    private double lng;
    @ApiModelProperty(value = "POI纬度")
    private double lat;
    @ApiModelProperty(value = "POI分类")
    private String industryclass;
    @ApiModelProperty(value = "POI子类")
    private String industrysubclass;
    @ApiModelProperty(value = "POI几何")
    private String geom;
    @ApiModelProperty(value = "POI的Geohash编码")
    private String geohash;
    @ApiModelProperty(value = "POI类别编号")
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
