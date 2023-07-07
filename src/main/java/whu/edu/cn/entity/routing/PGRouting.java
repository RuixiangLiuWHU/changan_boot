package whu.edu.cn.entity.routing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "最短路径规划结果实体")
public class PGRouting {
    @ApiModelProperty(value = "最短路径规划结果几何")
    private String geom;
    @ApiModelProperty(value = "最短路径规划结果长度")
    private double length;
    @ApiModelProperty(value = "最短路径规划结果时间")
    private double timing;

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getTiming() {
        return timing;
    }

    public void setTiming(double timing) {
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "PGRouting{" +
                "geom='" + geom + '\'' +
                ", length=" + length +
                ", timing=" + timing +
                '}';
    }
}
