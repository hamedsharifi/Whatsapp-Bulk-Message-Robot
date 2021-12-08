package ir.haytech.whatsapprobot.entities;

import ir.haytech.whatsapprobot.util.Constants;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity(name = "received_messages")
public class MessageReceived {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp datetime = new Timestamp(System.currentTimeMillis());
    private String fromPhone;
    private String toPhone = Constants.THIS_ACCOUNT_NUMBER;
    @Column(columnDefinition = "TEXT")
    private String message;


}
