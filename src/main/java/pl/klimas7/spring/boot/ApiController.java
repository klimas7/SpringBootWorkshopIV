package pl.klimas7.spring.boot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

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
}
