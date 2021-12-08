package ir.haytech.whatsapprobot.components;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import ir.haytech.whatsapprobot.entities.MessageReceived;
import ir.haytech.whatsapprobot.repositories.MessageReceivedRepository;
import it.auties.whatsapp4j.listener.WhatsappListener;
import it.auties.whatsapp4j.protobuf.chat.Chat;
import it.auties.whatsapp4j.protobuf.info.MessageInfo;
import it.auties.whatsapp4j.response.impl.json.UserInformationResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class WhatsappWebEventManager implements WhatsappListener {
    static boolean connected;
    static boolean readyToSend;
    @Autowired
    MessageReceivedRepository receivedRepository;

    public static boolean isConnected() {
        return connected;
    }

    public static boolean isReadyToSend() {
        return readyToSend;
    }

    public void onLoggedIn(@NonNull UserInformationResponse info) {
        System.out.println("Whatsapp connected :)");
        connected = true;
    }

    public void onDisconnected() {
        System.out.println("Whatsapp disconnected :(");
        connected = false;
    }

    @Override
    public void onChats() {
        WhatsappListener.super.onChats();
        System.out.println("Ready to send :)");
        readyToSend = true;
    }

    @Override
    public void onNewChat(@NonNull Chat chat) {
        WhatsappListener.super.onNewChat(chat);
        System.out.println("on new chat" + chat.displayName());
    }

    @Override
    public void onNewMessage(@NonNull Chat chat, @NonNull MessageInfo message) {
        WhatsappListener.super.onNewMessage(chat, message);
        System.out.println("on new message " + message.toString());
        MessageReceived messageReceived = new MessageReceived();
        messageReceived.setFromPhone(message.senderJid().split("@")[0]);
        messageReceived.setMessage(message.container().textMessage().text());
        receivedRepository.save(messageReceived);
    }

    @Override
    public void onQRCode(@NonNull BitMatrix qr) {
        WhatsappListener.super.onQRCode(qr);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(qr);
        File outputFile = new File("image.jpg");
        try {
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
