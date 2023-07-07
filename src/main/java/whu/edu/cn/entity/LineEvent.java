package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.postgis.LineString;
import whu.edu.cn.typehandler.LineStringTypeHandler;

@ApiModel(description = "线事件表的实体对象")
@TableName(value = "line", autoResultMap = true)
public class LineEvent {
    @TableId(value = "id")
    @ApiModelProperty(value = "线事件编号")
    private Integer id;
    @ApiModelProperty(value = "线事件起点测量值")
    private double fromm;
    @ApiModelProperty(value = "线事件终点测量值")
    private double tom;
    @ApiModelProperty(value = "线事件几何")
    private String geom;
    @ApiModelProperty(value = "线事件所在的灾害编号")
    private Integer disasterid;
    @ApiModelProperty(value = "线事件所在的道路编号")
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
