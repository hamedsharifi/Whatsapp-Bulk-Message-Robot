package ir.haytech.whatsapprobot.entities;

import ir.haytech.whatsapprobot.util.Constants;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity(name = "sent_messages")
public class MessageSent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp datetime = new Timestamp(System.currentTimeMillis());
    private String fromPhone = Constants.THIS_ACCOUNT_NUMBER;
    private String toPhone;
    @Column(columnDefinition = "TEXT")
    private String message;
}
