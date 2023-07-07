package whu.edu.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("whu.edu.cn.mapper")
public class ChanganApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(ChanganApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = "125.220.153.25";//换服务记得改
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        System.out.println("\n---------------------------------------------------------------\n\t" +
                "OGE数据中心程序正在运行...\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "swagger-ui: http://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "---------------------------------------------------------------");
    }

}
