package rushikesh.chatappdemo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rushikesh.chatappdemo.controller.request.SendMessageRequest;
import rushikesh.chatappdemo.entity.Message;
import rushikesh.chatappdemo.entity.User;
import rushikesh.chatappdemo.repository.MessageRepository;
import rushikesh.chatappdemo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Message save(String senderUsername, String receiverUsername, String content) {

        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findByUsername(receiverUsername)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message message = Message.builder()
                .sender(sender)
                .receiver(receiver)
                .content(content)
                .build();

        return messageRepository.save(message);
    }

    public List<Message> getChat(String user1, String user2) {
        return messageRepository.findChat(user1, user2);
    }
}


