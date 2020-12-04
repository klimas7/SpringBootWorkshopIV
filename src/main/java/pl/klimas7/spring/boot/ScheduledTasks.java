package pl.klimas7.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 5000)
    public void printCurrentTime() {
        log.info("Now is: {}", LocalDateTime.now());
    }
}
