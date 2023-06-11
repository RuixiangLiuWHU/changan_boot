package whu.edu.cn.entity.routing;

public class PGRouting {
    private String geom;
    private double length;
    private double timing;

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

    public double getTiming() {
        return timing;
    }

    public void setTiming(double timing) {
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "PGRouting{" +
                "geom='" + geom + '\'' +
                ", length=" + length +
                ", timing=" + timing +
                '}';
    }
}
