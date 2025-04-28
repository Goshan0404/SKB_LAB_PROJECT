package sinara_project.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Aspect
@Component
public class ApiCallLimitAspect {

    @Value("${api.max.calls}")
    private int maxApiCalls;

    private final AtomicInteger apiCallCount = new AtomicInteger(0);

    @Before("execution(* com.sinara_project.controller.OrderController.*(..))")
    public void checkApiCallLimit(JoinPoint joinPoint) throws Exception {
        int currentCount = apiCallCount.incrementAndGet();

        if (currentCount > maxApiCalls) {
            throw new Exception("Api call limit exceed");
        }

        log.info("Current Api calls:" + currentCount);
    }

    @Scheduled(fixedRate = 1800000)
    public void resetApiCallCount() {
        apiCallCount.set(0);
    }
}
