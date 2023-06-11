package whu.edu.cn.entity.routing;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "astarrouting", autoResultMap = true)
public class AStarRouting {
    private Integer id;
    private String geom;

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

    @Override
    public String toString() {
        return "PointRouteAM{" +
                "id=" + id +
                ", geom='" + geom + '\'' +
                '}';
    }
}
