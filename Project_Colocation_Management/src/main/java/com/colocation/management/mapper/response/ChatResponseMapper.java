package com.colocation.management.mapper.response;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.colocation.management.Dto.ChatResponseDto;
import com.colocation.management.Model.Chat;
import com.colocation.management.Model.ChatMessage;
import com.colocation.management.Repository.ChatMessageRepository;

@RequiredArgsConstructor
@Service
public class ChatResponseMapper {
    private final ChatMessageRepository chatMessageRepository;

    public ChatResponseDto toDto(Chat chat) {
        ChatResponseDto chatResponseDto = new ChatResponseDto();
        chatResponseDto.setId(chat.getId());
        chatResponseDto.setName(chat.getName());
        ChatMessage lastMessage = chatMessageRepository
                .findAllByChatId(chat.getId(),
                        PageRequest.of(0, 1,
                                Sort.by(Sort.Direction.DESC,"date")))
                .stream()
                .findFirst().orElse(new ChatMessage());
        chatResponseDto.setLastMessageSend(lastMessage.getMessage());
        chatResponseDto.setLastMessageSendTime(lastMessage.getDate());
        return chatResponseDto;
    }
}
