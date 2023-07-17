package whu.edu.cn.entity;


import java.io.Serializable;

public class Road implements Serializable {
    private String id;
    private String startVertexId;
    private String roadId;
    private String endVertexId;
    private String geom;
    private double length;
    private double cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getStartVertexId() {
        return startVertexId;
    }

    public void setStartVertexId(String startVertexId) {
        this.startVertexId = startVertexId;
    }

    public String getEndVertexId() {
        return endVertexId;
    }

    public void setEndVertexId(String endVertexId) {
        this.endVertexId = endVertexId;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
