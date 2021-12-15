package ir.haytech.whatsapprobot.entities;

import ir.haytech.whatsapprobot.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sent_messages")
public class MessageSent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp datetime;
    private String fromPhone;
    private String toPhone;
    @Column(columnDefinition = "TEXT")
    private String message;
}
