package ir.haytech.whatsapprobot.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity(name = "received_messages")
public class MessageReceived {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp datetime = new Timestamp(System.currentTimeMillis());
    private String fromPhone;
    private String message;


}
