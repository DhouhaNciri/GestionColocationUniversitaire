package com.colocation.management.Service;


import java.util.List;

import com.colocation.management.Model.Chat;

public interface ChatService {
    Chat get(Long id);

    Chat add(Chat chat);

    List<Chat> getAll();
}
