package db.schulung.Schulung061123.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
@Slf4j
public class TimerAspect {
//    private static final Logger log =
//            LoggerFactory.getLogger(TimerAspect.class);

    @Around("@annotation(timeMe)")
    Object timer(
            ProceedingJoinPoint joinPoint,
            TimeMe timeMe
    ) throws Throwable {

        try {
            Instant start = Instant.now();
            Object result = joinPoint.proceed();
            Instant end = Instant.now();

            log.info("Method {} took {} {}.",
                    joinPoint.getSignature().getName(),
                    timeMe.value().between(start, end),
                    timeMe.value().name());

            return result;

        } catch (Throwable throwable) {
            log.info("Error in timed Method {}.",
                    joinPoint.getSignature().getName());
            throw throwable;
        }
    }
}
