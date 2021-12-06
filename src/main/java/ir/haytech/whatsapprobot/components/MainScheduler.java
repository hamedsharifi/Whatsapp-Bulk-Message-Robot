package ir.haytech.whatsapprobot.components;


import ir.haytech.whatsapprobot.entities.Date;
import ir.haytech.whatsapprobot.repositories.DateRepository;
import ir.haytech.whatsapprobot.util.Constants;
import ir.haytech.whatsapprobot.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;

@Component
public class MainScheduler {

    @Autowired
    private DateRepository dateRepository;

    Date today;
    java.util.Date startTime;
    java.util.Date endTime;
    java.util.Date longSleepStartTime;
    java.util.Date longSleepEndTime;
    java.util.Date nextMessageTime;

    @Transactional
    @Scheduled(fixedRate = 1000, initialDelay = 5 * 1000)
    public void runCommandsToSendMessage() {
        java.util.Date now = new java.util.Date();
        if (today.getSentMessageCount() < today.getPermittedMessageCount()) {
            if (startTime.before(now) && endTime.after(now)) {
                if (longSleepStartTime.before(now) && now.after(longSleepEndTime)) {
                    if (now.after(nextMessageTime)) {
                        sendMessage();
                        dateRepository.setLastMessageSentTime(new Timestamp(now.getTime()), today.getId());
                        dateRepository.increaseSentCountByOne(today.getId());
                        nextMessageTime = new java.util.Date(now.getTime() + Utility.getDelayBeforeNextMessage(today.getPermittedMessageCount(), today.getStartTime(), today.getEndTime()));
                        System.out.println();
                    }
                }
            }
        }
    }

    @PostConstruct
    private void init() {
        today = dateRepository.findDateByDate(new Timestamp(Utility.getToday().getTime()));
        startTime = new java.util.Date(today.getStartTime().getTime());
        endTime = new java.util.Date(today.getEndTime().getTime());
        longSleepStartTime = new java.util.Date(today.getLongSleepTimeStart().getTime());
        longSleepEndTime = new java.util.Date(today.getLongSleepTimeEnd().getTime());
        nextMessageTime = new java.util.Date();
    }

    private void sendMessage() {
        System.out.println(new java.util.Date());
    }
}
