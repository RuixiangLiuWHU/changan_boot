package whu.edu.cn.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.transaction.TransactionRequiredException;
import java.util.List;
@Data
public class TreeData {
    @JSONField(name = "name")
    private String name;

    @JSONField(name = "key")
    private String key;

    @JSONField(name = "child")
    private List<TreeData> child;
    public TreeData(){}

    @Data
    public class TreeChild{
        @JSONField(name = "name")
        private String name;

        @JSONField(name = "key")
        private String key;

        @JSONField(name = "child")
        private List<TreeData>  child;
        public TreeChild(){}

        public class TreeChildChild{
            @JSONField(name = "name")
            private String name;

            @JSONField(name = "key")
            private String key;

            @JSONField(name = "child")
            private List<TreeData>  child;
            public TreeChildChild(){}

        }
    }

    @Override
    public String toString() {
        return "TreeData{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", child=" + child +
                '}';
    }
}
