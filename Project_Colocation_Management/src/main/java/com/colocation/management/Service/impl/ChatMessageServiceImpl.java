package com.colocation.management.Service.impl;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.colocation.management.Model.ChatMessage;
import com.colocation.management.Repository.ChatMessageRepository;
import com.colocation.management.Service.ChatMessageService;

@RequiredArgsConstructor
@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage add(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getAllByChatId(Long chatId) {
        return chatMessageRepository.findAllByChatId(chatId);
    }
}
