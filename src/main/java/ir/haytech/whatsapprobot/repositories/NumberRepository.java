package ir.haytech.whatsapprobot.repositories;

import ir.haytech.whatsapprobot.entities.MessageSent;
import ir.haytech.whatsapprobot.entities.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepository extends JpaRepository<Number, Long> {
    Number findFirstByOrderByIdAsc();

    void deleteById(long id);
}
