package pl.klimas7.spring.boot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/{name}")
    public ResponseEntity<String> test(@PathVariable String name) {
        log.info("TEST: begin {} ====================", name);
        var sleep = ThreadLocalRandom.current().nextInt(500, 1001);
        safeSleep(sleep);
        if ("error".equals(name)) {
            throw new RuntimeException("Upps " + sleep);
        }
        log.info("TEST: end {} ====================", name);
        return ResponseEntity.ok("Test: " + name + " : " + sleep);
    }

    private void safeSleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            log.warn(e.getMessage());
        }
    }
}
