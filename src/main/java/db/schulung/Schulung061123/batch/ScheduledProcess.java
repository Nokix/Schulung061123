package db.schulung.Schulung061123.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledProcess {
    private int i = 0;

//    @Scheduled(fixedRate = 1000)
    public void count() {
        System.out.println(i++);
    }
}
