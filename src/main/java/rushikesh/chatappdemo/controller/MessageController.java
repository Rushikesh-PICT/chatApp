package rushikesh.chatappdemo.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import rushikesh.chatappdemo.controller.request.SendMessageRequest;
import rushikesh.chatappdemo.entity.Message;

import rushikesh.chatappdemo.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{username}")
    public List<Message> getChat(
            @PathVariable String username,
            Authentication auth
    ) {
        return messageService.getChat(auth.getName(), username);
    }
}


