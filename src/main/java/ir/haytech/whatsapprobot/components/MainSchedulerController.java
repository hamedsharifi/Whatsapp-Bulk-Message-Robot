package ir.haytech.whatsapprobot.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MainSchedulerController {
    private static final String SCHEDULED_TASKS = "scheduledTasks";

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private MainScheduler mainScheduler;

    public void stopSchedule() {
        postProcessor.postProcessBeforeDestruction(mainScheduler, SCHEDULED_TASKS);
    }

    public void startSchedule() {
        postProcessor.postProcessAfterInitialization(mainScheduler, SCHEDULED_TASKS);
    }
}
