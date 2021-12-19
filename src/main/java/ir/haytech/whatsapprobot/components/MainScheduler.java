package ir.haytech.whatsapprobot.components;


import ir.haytech.whatsapprobot.entities.Date;
import ir.haytech.whatsapprobot.entities.MessageSent;
import ir.haytech.whatsapprobot.entities.Number;
import ir.haytech.whatsapprobot.repositories.DateRepository;
import ir.haytech.whatsapprobot.repositories.MessageSentRepository;
import ir.haytech.whatsapprobot.repositories.NumberRepository;
import ir.haytech.whatsapprobot.util.Constants;
import ir.haytech.whatsapprobot.util.Utility;
import it.auties.whatsapp4j.protobuf.chat.Chat;
import it.auties.whatsapp4j.protobuf.message.standard.ImageMessage;
import it.auties.whatsapp4j.response.impl.json.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;

@Component
public class MainScheduler {

    @Autowired
    private DateRepository dateRepository;

    @Autowired
    private NumberRepository numberRepository;

    @Autowired
    private MessageSentRepository messageSentRepository;

    @Autowired
    private WhatsappWeb whatsappWeb;

    Date today;
    java.util.Date startTime;
    java.util.Date endTime;
    java.util.Date longSleepStartTime;
    java.util.Date longSleepEndTime;
    java.util.Date nextMessageTime;

    @Transactional
    @Scheduled(fixedRate = 31000, initialDelay = 12 * 1000)
    public void runCommandsToSendMessage() {
        if (WhatsappWebEventManager.isConnected()) {
            if (WhatsappWebEventManager.isReadyToSend()) {
                java.util.Date now = new java.util.Date();
                if (today.getSentMessageCount() < today.getPermittedMessageCount()) {
                    if (startTime.before(now) && endTime.after(now)) {
                        if (longSleepStartTime.before(now) && now.after(longSleepEndTime)) {
                            if (now.after(nextMessageTime)) {
                                Number topRecord = numberRepository.findFirstByOrderByIdAsc();
                                if (topRecord != null) {
                                    sendMessage(topRecord);
                                    dateRepository.setLastMessageSentTime(new Timestamp(now.getTime()), today.getId());
                                    dateRepository.increaseSentCountByOne(today.getId());
                                    nextMessageTime = new java.util.Date(now.getTime() + Utility.getDelayBeforeNextMessage(today.getPermittedMessageCount(), today.getStartTime(), today.getEndTime()));
                                }
                            }
                        }
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

    private void sendMessage(Number number) {
        /*var chat = whatsappWeb.getManager().findChatByJid("989114900173@s.whatsapp.net");
        try {
            File file = new File("E:/object1.png");
            var fileMedia = Files.readAllBytes(file.toPath()); // Read a media from a file
            var urlMedia = new URL("file:/" + file.toString()).openStream().readAllBytes(); // Read a media from an url
            var image = ImageMessage.newImageMessage() // Create a new image message builder
                    .media(urlMedia) // Set the image of this message
                    .create(); // Create the message
            whatsappWeb.getWhatsappAPI().sendMessage(chat.get(), image); // Send the image message
            whatsappWeb.getWhatsappAPI().sendMessage(chat.get(), "I'm Live!");
            System.out.println(new java.util.Date());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Chat newChat = new Chat();
        var chat = whatsappWeb.getManager().addChat(newChat.jid(number.getNumber() + "@s.whatsapp.net"));
        try {
            File file = new File("E:/object1.png");
            var fileMedia = Files.readAllBytes(file.toPath()); // Read a media from a file
            var urlMedia = new URL("file:/" + file.toString()).openStream().readAllBytes(); // Read a media from an url

            var image = ImageMessage.newImageMessage() // Create a new image message builder
                    .media(urlMedia) // Set the image of this message
                    .create(); // Create the message
            whatsappWeb.getWhatsappAPI().sendMessage(chat, image); // Send the image message
            Thread.sleep(100);
        } catch (Exception e) {
        }
        var sendMessageResult = whatsappWeb.getWhatsappAPI().sendMessage(chat, Constants.MESSAGE);
        messageSentRepository.save(MessageSent.builder().message(Constants.MESSAGE).toPhone(number.getNumber()).fromPhone(Constants.THIS_ACCOUNT_NUMBER).datetime(new Timestamp(System.currentTimeMillis())).build());
        numberRepository.deleteById(number.getId());
        System.out.println(new java.util.Date() + "--->" + number.getNumber());
        try {
            System.out.println(sendMessageResult.get().status());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
