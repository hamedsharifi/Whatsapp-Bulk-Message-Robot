package ir.haytech.whatsapprobot.repositories;

import ir.haytech.whatsapprobot.entities.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
    Date findTopByOrderByIdDesc();

    Date findDateByDate(Timestamp timestamp);

    @Modifying
    @Query("UPDATE dates d set d.sentMessageCount = d.sentMessageCount + 1 WHERE d.id = :id")
    void increaseSentCountByOne(long id);

    @Modifying
    @Query("UPDATE dates d set d.lastMessageSentTime = :now WHERE d.id = :id")
    void setLastMessageSentTime(Timestamp now, long id);
}
