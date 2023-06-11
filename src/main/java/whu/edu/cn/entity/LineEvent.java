package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.postgis.LineString;
import whu.edu.cn.typehandler.LineStringTypeHandler;

@TableName(value = "line", autoResultMap = true)
public class LineEvent {
    @TableId(value = "id")
    private Integer id;
    private double fromm;
    private double tom;
    private String geom;
    private Integer disasterid;
    private Integer routeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getFromm() {
        return fromm;
    }

    public void setFromm(double fromm) {
        this.fromm = fromm;
    }

    public double getTom() {
        return tom;
    }

    public void setTom(double tom) {
        this.tom = tom;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public Integer getDisasterid() {
        return disasterid;
    }

    public void setDisasterid(Integer disasterid) {
        this.disasterid = disasterid;
    }

    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
    }

    @Override
    public String toString() {
        return "LineEvent{" +
                "id=" + id +
                ", fromm=" + fromm +
                ", tom=" + tom +
                ", geom='" + geom + '\'' +
                ", disasterid=" + disasterid +
                ", routeid=" + routeid +
                '}';
    }
}
