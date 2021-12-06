package ir.haytech.whatsapprobot.repositories;

import ir.haytech.whatsapprobot.entities.MessageSent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageSentRepository extends JpaRepository<MessageSent, Long> {
}
