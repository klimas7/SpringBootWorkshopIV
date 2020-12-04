package pl.klimas7.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ScheduledTasks {

    @Scheduled(fixedRateString = "${scheduled.time}")
    public void printCurrentTime() {
        log.info("Now is: {}", LocalDateTime.now());
        log.debug("Now is: {}", LocalDateTime.now());
    }

    @Scheduled(cron = "${scheduled.cron}")
    public void printCurrentTimeCrone() {
        log.info("Cron: Now is: {}", LocalDateTime.now());
    }

}
