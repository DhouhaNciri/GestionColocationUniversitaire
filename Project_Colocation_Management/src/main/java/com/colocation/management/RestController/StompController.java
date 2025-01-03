package com.colocation.management.RestController;


import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.colocation.management.Dto.ChatMessageRequestDto;
import com.colocation.management.Dto.ChatMessageResponseDto;
import com.colocation.management.Model.ChatMessage;
import com.colocation.management.Service.ChatMessageService;
import com.colocation.management.Service.ChatService;
import com.colocation.management.mapper.request.ChatMessageRequestMapper;
import com.colocation.management.mapper.response.ChatMessageResponseMapper;

@RequiredArgsConstructor
@Controller
public class StompController {
    private final ChatService chatService;
    private final ChatMessageService chatMessageService;
    private final ChatMessageRequestMapper chatMessageRequestMapper;
    private final ChatMessageResponseMapper chatMessageResponseMapper;

    @MessageMapping("/chats/{chatId}")
    @SendTo("/topic/chats")
    public ChatMessageResponseDto chat(@DestinationVariable Long chatId,
                                       ChatMessageRequestDto dto) {
        ChatMessage chatMessage = chatMessageRequestMapper.toModel(dto);
        chatMessage.setChat(chatService.get(chatId));
        chatMessage.setDate(LocalDateTime.now());
        return chatMessageResponseMapper.toDto(chatMessageService.add(chatMessage));
    }
}
