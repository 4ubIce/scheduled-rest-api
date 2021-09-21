package home.kirill.scheduledrestapi.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger logger = Logger.getLogger(ScheduledTasks.class.getName());

    @Scheduled(fixedRate = 5000)
    public void importTask() {
        logger.debug("Import task start " + dateFormat.format(new Date()));
        System.out.println("Import task start " + dateFormat.format(new Date()));
    }

}
