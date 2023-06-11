package whu.edu.cn.entity.aftermatching;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "pointrouteam", autoResultMap = true)
public class PointRouteAM {
    private Integer id;
    private String geom;
    private Integer disasterid;
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
