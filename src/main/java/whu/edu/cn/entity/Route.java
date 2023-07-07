package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "道路表的实体对象")
@TableName(value = "ways", autoResultMap = true)
public class Route {
    @ApiModelProperty(value = "道路编号")
    private Integer gid;
    @ApiModelProperty(value = "道路名称")
    private String name;
    @ApiModelProperty(value = "道路起点编号")
    private Integer source;
    @ApiModelProperty(value = "道路终点编号")
    private Integer target;
    @ApiModelProperty(value = "道路正向权重")
    private double cost;
    @ApiModelProperty(value = "道路反向权重")
    private double reverse_cost;
    @ApiModelProperty(value = "道路起点经度")
    private double x1;
    @ApiModelProperty(value = "道路起点纬度")
    private double y1;
    @ApiModelProperty(value = "道路终点经度")
    private double x2;
    @ApiModelProperty(value = "道路终点纬度")
    private double y2;
    @ApiModelProperty(value = "道路正向限速速度")
    private double maxspeed_forward;
    @ApiModelProperty(value = "道路反向限速速度")
    private double maxspeed_backward;
    @ApiModelProperty(value = "道路优先级权重")
    private double priority;
    @ApiModelProperty(value = "道路几何")
    private String geom;
    @ApiModelProperty(value = "道路7位Geohash编码")
    private String geohash7;
    @ApiModelProperty(value = "道路6位Geohash编码")
    private String geohash6;
    @ApiModelProperty(value = "道路5位Geohash编码")
    private String geohash5;
    @ApiModelProperty(value = "道路4位Geohash编码")
    private String geohash4;
    @ApiModelProperty(value = "道路3位Geohash编码")
    private String geohash3;
    @ApiModelProperty(value = "道路所有位数的Geohash编码")
    private String[] geohash;

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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getReverse_cost() {
        return reverse_cost;
    }

    public void setReverse_cost(double reverse_cost) {
        this.reverse_cost = reverse_cost;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getMaxspeed_forward() {
        return maxspeed_forward;
    }

    public void setMaxspeed_forward(double maxspeed_forward) {
        this.maxspeed_forward = maxspeed_forward;
    }

    public double getMaxspeed_backward() {
        return maxspeed_backward;
    }

    public void setMaxspeed_backward(double maxspeed_backward) {
        this.maxspeed_backward = maxspeed_backward;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getGeohash7() {
        return geohash7;
    }

    public void setGeohash7(String geohash7) {
        this.geohash7 = geohash7;
    }

    public String getGeohash6() {
        return geohash6;
    }

    public void setGeohash6(String geohash6) {
        this.geohash6 = geohash6;
    }

    public String getGeohash5() {
        return geohash5;
    }

    public void setGeohash5(String geohash5) {
        this.geohash5 = geohash5;
    }

    public String getGeohash4() {
        return geohash4;
    }

    public void setGeohash4(String geohash4) {
        this.geohash4 = geohash4;
    }

    public String getGeohash3() {
        return geohash3;
    }

    public void setGeohash3(String geohash3) {
        this.geohash3 = geohash3;
    }

    public String[] getGeohash() {
        return geohash;
    }

    public void setGeohash(String[] geohash) {
        this.geohash = geohash;
    }

    @Override
    public String toString() {
        return "Route{" +
                "gid=" + gid +
                ", name='" + name + '\'' +
                ", source=" + source +
                ", target=" + target +
                ", cost=" + cost +
                ", reverse_cost=" + reverse_cost +
                ", x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", maxspeed_forward=" + maxspeed_forward +
                ", maxspeed_backward=" + maxspeed_backward +
                ", priority=" + priority +
                ", geom='" + geom + '\'' +
                ", geohash7='" + geohash7 + '\'' +
                ", geohash6='" + geohash6 + '\'' +
                ", geohash5='" + geohash5 + '\'' +
                ", geohash4='" + geohash4 + '\'' +
                ", geohash3='" + geohash3 + '\'' +
                ", geohash=" + String.join(",", geohash) +
                '}';
    }
}
