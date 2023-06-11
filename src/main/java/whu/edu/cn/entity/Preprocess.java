package whu.edu.cn.entity;

public class Preprocess {
    private Integer gid;
    private Long source;
    private Long target;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Preprocess{" +
                "gid=" + gid +
                ", source=" + source +
                ", target=" + target +
                '}';
    }
}
