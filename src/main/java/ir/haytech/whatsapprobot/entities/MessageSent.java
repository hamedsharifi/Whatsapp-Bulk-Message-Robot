package ir.haytech.whatsapprobot.entities;

import ir.haytech.whatsapprobot.util.Constants;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity(name = "sent_messages")
public class MessageSent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp datetime = new Timestamp(System.currentTimeMillis());
    private String from = Constants.THIS_ACCOUNT_NUMBER;
    private String to;
    private String message;
}
