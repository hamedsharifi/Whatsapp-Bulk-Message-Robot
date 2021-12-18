package ir.haytech.whatsapprobot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "numbers")
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String number;
    @Column(nullable = true)
    boolean messageSent;
}
