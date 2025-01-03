package com.colocation.management.mapper.response;


import org.springframework.stereotype.Service;

import com.colocation.management.Dto.ChatMessageResponseDto;
import com.colocation.management.Model.ChatMessage;

@Service
public class ChatMessageResponseMapper {
    public ChatMessageResponseDto toDto(ChatMessage chatMessage) {
        ChatMessageResponseDto chatMessageResponseDto = new ChatMessageResponseDto();
        chatMessageResponseDto.setId(chatMessage.getId());
        chatMessageResponseDto.setMessage(chatMessage.getMessage());
        chatMessageResponseDto.setFrom(chatMessage.getSendFrom().getFirstName());
        chatMessageResponseDto.setDate(chatMessage.getDate());
        chatMessageResponseDto.setChatId(chatMessage.getChat().getId());
        return chatMessageResponseDto;
    }
}
