package rushikesh.chatappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rushikesh.chatappdemo.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("""
        select m from Message m
        where (m.sender.username = :u1 and m.receiver.username = :u2)
           or (m.sender.username = :u2 and m.receiver.username = :u1)
        order by m.timestamp
    """)
    List<Message> findChat(String u1, String u2);
}
