package whu.edu.cn.entity.routing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "astarroutingseparate", autoResultMap = true)
public class AStarRoutingSeparate {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer start;
    private Integer end;
    private String geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    @Override
    public String toString() {
        return "AStarRoutingSeparate{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", geom='" + geom + '\'' +
                '}';
    }
}
