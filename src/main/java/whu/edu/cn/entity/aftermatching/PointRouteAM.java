package whu.edu.cn.entity.aftermatching;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "匹配后的点事件实体对象")
@TableName(value = "pointrouteam", autoResultMap = true)
public class PointRouteAM {
    @ApiModelProperty(value = "点事件编号")
    private Integer id;
    @ApiModelProperty(value = "点事件几何")
    private String geom;
    @ApiModelProperty(value = "灾害编号")
    private Integer disasterid;
    @ApiModelProperty(value = "道路编号")
    private Integer routeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "PointRouteAM{" +
                "id=" + id +
                ", geom='" + geom + '\'' +
                ", disasterid=" + disasterid +
                ", routeid=" + routeid +
                '}';
    }
}
