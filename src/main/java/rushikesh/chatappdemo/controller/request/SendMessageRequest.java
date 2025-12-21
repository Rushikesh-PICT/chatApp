package rushikesh.chatappdemo.controller.request;

import lombok.Data;

@Data
public class SendMessageRequest {
    private String receiver;
    private String content;
}
