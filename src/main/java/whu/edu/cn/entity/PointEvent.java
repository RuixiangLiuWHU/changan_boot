package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.postgis.Point;
import whu.edu.cn.typehandler.PointTypeHandler;

@ApiModel(description = "点事件的实体对象")
@TableName(value = "point", autoResultMap = true)
public class PointEvent {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "点事件编号")
    private Integer id;
    @ApiModelProperty(value = "点事件测量值")
    private double m;
    @ApiModelProperty(value = "点事件几何")
    private String geom;
    @ApiModelProperty(value = "点事件所在的灾害编号")
    private Integer disasterid;
    @ApiModelProperty(value = "点事件所在的道路编号")
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
