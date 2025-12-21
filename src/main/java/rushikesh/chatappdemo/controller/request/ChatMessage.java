package rushikesh.chatappdemo.controller.request;

import lombok.Data;

@Data
public class ChatMessage {
    private String receiver;
    private String content;
}
