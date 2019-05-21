package io.pyxis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GraphiqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphiqlDemoApplication.class, args);
    }

}
