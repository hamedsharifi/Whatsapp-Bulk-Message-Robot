package ir.haytech.whatsapprobot.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity(name = "dates")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp date;
    private Timestamp startTime;
    private Timestamp endTime;
    private int permittedMessageCount;
    private Timestamp longSleepTimeStart;
    private Timestamp longSleepTimeEnd;
    private int sentMessageCount;
    private Timestamp lastMessageSentTime;
}
