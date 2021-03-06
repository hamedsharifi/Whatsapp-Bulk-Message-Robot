package ir.haytech.whatsapprobot.components;


import it.auties.whatsapp4j.manager.WhatsappDataManager;
import it.auties.whatsapp4j.whatsapp.WhatsappAPI;
import it.auties.whatsapp4j.whatsapp.WhatsappConfiguration;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Data
@Component
public class WhatsappWeb implements CommandLineRunner {

    private WhatsappAPI whatsappAPI;
    private WhatsappDataManager manager;
    @Autowired
    private WhatsappWebEventManager webEventManager;

    @Override
    public void run(String... args) throws Exception {
        WhatsappConfiguration configuration = WhatsappConfiguration.builder()
                .whatsappUrl("wss://web.whatsapp.com/ws") // WhatsappWeb's WebSocket URL
                .requestTag("HaytechR") // The tag used for requests made to WhatsappWeb's WebSocket
                .description("HaytechR") // The description provided to Whatsapp during the authentication process
                .shortDescription("HaytechR") // An acronym for the description
                .reconnectWhenDisconnected((reason) -> true) // Determines whether the connection should be reclaimed
                .async(true) // Determines whether requests sent to whatsapp should be asyncronous or not
                .build(); // Builds an instance of WhatsappConfiguration
        whatsappAPI = new WhatsappAPI();

        whatsappAPI.registerListener(webEventManager);
        manager = whatsappAPI.manager();
        whatsappAPI.connect();
    }
}
