package pl.klimas7.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class);
    }
}

/*
http://localhost:8081/actuator
 */

/*
https://devops.datenkollektiv.de/banner.txt/index.html
 */

/*
Server code
https://github.com/klimas7/SpringBootAdmin
 */
