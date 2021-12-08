package ir.haytech.whatsapprobot.components;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import it.auties.whatsapp4j.listener.WhatsappListener;
import it.auties.whatsapp4j.response.impl.json.UserInformationResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@Component
public class WhatsappWebEventManager implements WhatsappListener {
    static boolean isConnected;

    public void onLoggedIn(@NonNull UserInformationResponse info) {
        System.out.println("Whatsapp Connected :)");
        isConnected = true;
    }

    public void onDisconnected() {
        System.out.println("Whatsapp Disconnected :(");
        isConnected = false;
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

    public boolean isConnected() {
        return isConnected;
    }
}
