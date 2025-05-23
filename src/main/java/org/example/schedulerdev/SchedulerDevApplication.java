package org.example.schedulerdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SchedulerDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerDevApplication.class, args);
    }

}
