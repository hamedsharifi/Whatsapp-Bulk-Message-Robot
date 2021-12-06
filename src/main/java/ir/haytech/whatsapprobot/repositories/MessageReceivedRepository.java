package ir.haytech.whatsapprobot.repositories;

import ir.haytech.whatsapprobot.entities.MessageReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReceivedRepository extends JpaRepository<MessageReceived, Long> {
}
