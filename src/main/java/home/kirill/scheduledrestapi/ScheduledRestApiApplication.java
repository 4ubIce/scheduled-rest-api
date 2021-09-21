package home.kirill.scheduledrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduledRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledRestApiApplication.class, args);
    }

}
