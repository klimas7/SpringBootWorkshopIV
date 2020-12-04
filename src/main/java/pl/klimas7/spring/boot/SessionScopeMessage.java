package pl.klimas7.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopeMessage {
    private final AtomicInteger counter = new AtomicInteger(0);

    public SessionScopeMessage() {
        log.info("Create " + SessionScopeMessage.class.getName());
    }

    public String getMessage() {
        String message = "SessionScope: " + counter.incrementAndGet();
        log.info(message);
        return message;
    }
}
