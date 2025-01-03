package com.colocation.management.Service.impl;


import java.util.List;
import java.util.NoSuchElementException;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.colocation.management.Model.Chat;
import com.colocation.management.Repository.ChatRepository;
import com.colocation.management.Service.ChatService;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public Chat get(Long id) {
        return chatRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("No such char with id " + id));
    }

    @Override
    public Chat add(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAllByOrderByCreatedOnDesc();
    }
}
