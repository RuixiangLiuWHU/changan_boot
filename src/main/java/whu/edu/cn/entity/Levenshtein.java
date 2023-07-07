package whu.edu.cn.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(description = "Levenshtein距离类")
public class Levenshtein implements Comparable<Levenshtein> {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "相似度")
    private Double similarity;

    @Override
    public int compareTo(Levenshtein o) {
        return this.similarity.compareTo(o.similarity);
    }
    public Levenshtein(String name, double similarity){
        this.name = name;
        this.similarity = similarity;
    }
}
