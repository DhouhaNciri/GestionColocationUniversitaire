package com.colocation.management.mapper.request;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.colocation.management.Dto.ChatMessageRequestDto;
import com.colocation.management.Model.ChatMessage;
import com.colocation.management.Model.User;
import com.colocation.management.Repository.UserRepository;

@RequiredArgsConstructor
@Service
public class ChatMessageRequestMapper {
    private final UserRepository userRepository;

    public ChatMessage toModel(ChatMessageRequestDto dto) {
        ChatMessage chatMessage = new ChatMessage();
        User user = userRepository.findByEmail(dto.getFrom()).orElseThrow();
        chatMessage.setSendFrom(user);
        chatMessage.setMessage(dto.getMessage());
        return chatMessage;
    }
}
