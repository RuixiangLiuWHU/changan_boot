package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "ways", autoResultMap = true)
public class Route {
    private Integer gid;
    private String name;
    private Integer source;
    private Integer target;
    private double cost;
    private double reverse_cost;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double maxspeed_forward;
    private double maxspeed_backward;
    private double priority;
    private String geom;
    private String geohash7;
    private String geohash6;
    private String geohash5;
    private String geohash4;
    private String geohash3;
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
