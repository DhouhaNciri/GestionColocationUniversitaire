package com.colocation.management.Service;


import java.util.List;

import com.colocation.management.Model.ChatMessage;

public interface ChatMessageService {
    ChatMessage add(ChatMessage chatMessage);

    List<ChatMessage> getAllByChatId(Long chatId);
}
