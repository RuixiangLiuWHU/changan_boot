package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.postgis.Point;
import whu.edu.cn.typehandler.PointTypeHandler;


@TableName(value = "point", autoResultMap = true)
public class PointEvent {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private double m;
    private String geom;
    private Integer disasterid;
    private Integer routeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
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
        return "PointEvent{" +
                "id=" + id +
                ", m=" + m +
                ", geom='" + geom + '\'' +
                ", disasterid=" + disasterid +
                ", routeid=" + routeid +
                '}';
    }
}
