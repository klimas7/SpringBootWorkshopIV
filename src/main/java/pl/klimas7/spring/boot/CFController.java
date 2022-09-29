package pl.klimas7.spring.boot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/cf")
public class CFController {
    private final RestTemplate restTemplate;

    private static final ExecutorService executors = Executors.newFixedThreadPool(2);

    @GetMapping
    public ResponseEntity<String> test() {
        log.info("CF: Begin ==============================");
        CompletableFuture<String> cf_1 = CompletableFuture.supplyAsync(() -> invokeRest("test_1"), executors);
        CompletableFuture<String> cf_2 = CompletableFuture.supplyAsync(() -> invokeRest("test_2"), executors);
        CompletableFuture<String> cf_3 = CompletableFuture.supplyAsync(() -> invokeRest("test_3"), executors);
        CompletableFuture<String> cf_4 = CompletableFuture.supplyAsync(() -> invokeRest("test_4"), executors);
        CompletableFuture<String> cf_5 = CompletableFuture.supplyAsync(() -> invokeRest("test_5"), executors);
        CompletableFuture<String> cf_6 = CompletableFuture.supplyAsync(() -> invokeRest("test_6"), executors);

        Map<String, List<String>> result = new ConcurrentHashMap<>();
        var l1 = List.of(cf_1, cf_2, cf_3);
        var l2 = List.of(cf_4, cf_5, cf_6);

        var cfl_1 = CompletableFuture.allOf(l1.toArray(new CompletableFuture[0]))
                        .thenApplyAsync(v -> l1.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()), executors)
                        .thenApply(l -> result.put("l1", l));

        var cfl_2 = CompletableFuture.allOf(l1.toArray(new CompletableFuture[0]))
                .thenApplyAsync(v -> l2.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()), executors)
                .thenApply(l -> result.put("l2", l));

        AtomicReference<String> master = new AtomicReference<>("");
        CompletableFuture.allOf(cfl_1, cfl_2)
                        .whenCompleteAsync((r, throwable) -> {
                            if (throwable != null) {
                                log.error(throwable.getMessage(), throwable);
                            }

                            master.updateAndGet(v -> v + result.get("l1"));
                            master.updateAndGet(v -> v + result.get("l2"));
                            log.info("----> MASTER {} {}", master, r);
                        }, executors);

        log.info("CF: End ================================");
        return ResponseEntity.ok(master.get());
    }

    private String invokeRest(String name) {
        log.info("CF in begin {} ----------", name);
        var response = restTemplate.getForEntity("http://localhost:8081/test/" + name, String.class);
        var body = response.getBody();
        log.info("CF in end {} {} ------------", name, body);
        return body;
    }
}
