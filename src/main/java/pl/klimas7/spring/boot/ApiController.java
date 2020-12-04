package pl.klimas7.spring.boot;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final SessionScopeMessage sessionScopeMessage;
    private final RequestScopeMessage requestScopeMessage;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello2")
    public ResponseEntity<String> hello2(@RequestParam String name) {
//        return new ResponseEntity<>("hello", HttpStatus.OK);
        return ResponseEntity.ok("hello2: " + name);
    }

    @GetMapping("/hello/{name}")
    public ResponseEntity<String> hello3(@PathVariable String name) {
        return ResponseEntity.ok("hello3: " + name);
    }

    @GetMapping("/session")
    public String session() {
        return sessionScopeMessage.getMessage();
    }

    @GetMapping("/request")
    public String request() {
        return requestScopeMessage.getMessage();
    }
}
