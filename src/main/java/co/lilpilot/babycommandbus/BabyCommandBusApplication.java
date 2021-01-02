package co.lilpilot.babycommandbus;

import co.lilpilot.babycommandbus.core.Bootstrap;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BabyCommandBusApplication {

    @Bean(name = "bootstrapBean", initMethod = "init")
    public Bootstrap bootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setPackages(Lists.newArrayList("co.lilpilot.babycommandbus.editor"));
        return bootstrap;
    }

    public static void main(String[] args) {
        SpringApplication.run(BabyCommandBusApplication.class, args);
    }

}
