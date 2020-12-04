package pl.klimas7.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeMessage {
    private final AtomicInteger counter = new AtomicInteger(0);

    public RequestScopeMessage() {
        log.info("Create: " + RequestScopeMessage.class.getName());
    }

    public String getMessage() {
        String message = "RequestScope: " + counter.incrementAndGet();
        log.info(message);
        return message;
    }
}
