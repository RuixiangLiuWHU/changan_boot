package whu.edu.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("whu.edu.cn.mapper")
public class ChanganApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChanganApplication.class, args);
    }

}
