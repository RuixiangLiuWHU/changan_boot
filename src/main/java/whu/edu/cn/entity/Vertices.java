package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "道路节点表的实体对象")
@TableName(value = "ways_vertices_pgr", autoResultMap = true)
public class Vertices {
    @ApiModelProperty(value = "道路节点编号")
    private Integer id;
    @ApiModelProperty(value = "道路节点经度")
    private double lon;
    @ApiModelProperty(value = "道路节点纬度")
    private double lat;
    @ApiModelProperty(value = "道路节点几何")
    private String geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    @Override
    public String toString() {
        return "Vertices{" +
                "id=" + id +
                ", lon=" + lon +
                ", lat=" + lat +
                ", geom='" + geom + '\'' +
                '}';
    }
}
