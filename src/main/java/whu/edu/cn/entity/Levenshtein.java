package whu.edu.cn.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Levenshtein implements Comparable<Levenshtein> {
    private String name;
    private Double similarity;

    @Override
    public int compareTo(Levenshtein o) {
        int temp = this.similarity.compareTo(o.similarity);
        return temp;
    }
    public Levenshtein(String name, double similarity){
        this.name = name;
        this.similarity = similarity;
    }
}
