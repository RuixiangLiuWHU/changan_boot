package whu.edu.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "ways_vertices_pgr", autoResultMap = true)
public class Vertices {
    private Integer id;
    private double lon;
    private double lat;
    private String geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    @Override
    public String toString() {
        return "Vertices{" +
                "id=" + id +
                ", lon=" + lon +
                ", lat=" + lat +
                ", geom='" + geom + '\'' +
                '}';
    }
}
