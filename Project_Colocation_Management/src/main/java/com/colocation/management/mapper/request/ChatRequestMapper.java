package com.colocation.management.mapper.request;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.colocation.management.Dto.ChatRequestDto;
import com.colocation.management.Model.Chat;
import com.colocation.management.Model.User;
import com.colocation.management.Repository.UserRepository;

@RequiredArgsConstructor
@Service
public class ChatRequestMapper {
    private final UserRepository userRepository;

    public Chat toModel(ChatRequestDto chatRequestDto) {
        Chat chat = new Chat();
        User user = userRepository.findByEmail(chatRequestDto.getNickName()).orElseThrow();
        chat.setName(chatRequestDto.getName());
        chat.setUser(user);
        return chat;
    }
}
