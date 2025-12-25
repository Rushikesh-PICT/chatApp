package rushikesh.chatappdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import rushikesh.chatappdemo.controller.request.ChatMessage;
import rushikesh.chatappdemo.entity.Message;
import rushikesh.chatappdemo.service.MessageService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/chat")
    public void send(ChatMessage message, Principal principal) {

        if (principal == null) {
            System.out.println("❌ Principal is NULL");
            return;
        }

        String sender = principal.getName();
        String receiver = message.getReceiver();

        System.out.println("📨 WS MESSAGE: " + sender + " -> " + receiver);

        Message saved = messageService.save(
                sender,
                receiver,
                message.getContent()
        );

        messagingTemplate.convertAndSendToUser(
                receiver,
                "/queue/messages",
                saved
        );

        messagingTemplate.convertAndSendToUser(
                sender,
                "/queue/messages",
                saved
        );
    }
}



