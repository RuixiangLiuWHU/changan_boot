package whu.edu.cn.entity;


import java.io.Serializable;

public class Vertex implements Serializable {

    private String vertexId;
    private Double lat;
    private Double lon;

    public String getVertexId() {
        return vertexId;
    }

    public void setVertexId(String vertexId) {
        this.vertexId = vertexId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "vertexId='" + vertexId + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
