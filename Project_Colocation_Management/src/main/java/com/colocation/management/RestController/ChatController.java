package com.colocation.management.RestController;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colocation.management.Dto.ChatMessageResponseDto;
import com.colocation.management.Dto.ChatRequestDto;
import com.colocation.management.Dto.ChatResponseDto;
import com.colocation.management.Model.Chat;
import com.colocation.management.Service.ChatMessageService;
import com.colocation.management.Service.ChatService;
import com.colocation.management.mapper.request.ChatRequestMapper;
import com.colocation.management.mapper.response.ChatMessageResponseMapper;
import com.colocation.management.mapper.response.ChatResponseMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;
    private final ChatMessageService chatMessageService;
    private final ChatMessageResponseMapper chatMessageResponseMapper;
    private final ChatRequestMapper chatRequestMapper;
    private final ChatResponseMapper chatResponseMapper;

    @PostMapping
    public ChatResponseDto createChat(@RequestBody ChatRequestDto chatRequestDto) {
        Chat chat = chatRequestMapper.toModel(chatRequestDto);
        chat.setCreatedOn(LocalDateTime.now());
        return chatResponseMapper.toDto(chatService.add(chat));
    }

    @GetMapping
    public List<ChatResponseDto> getAllChats() {
        return chatService.getAll().stream()
                .map(chatResponseMapper::toDto)
                .collect(Collectors.toList()); // Utilisation de Collectors.toList()
    }

    @GetMapping("/{chatId}")
    public List<ChatMessageResponseDto> getAllMessages(
            @PathVariable Long chatId) {
        return chatMessageService.getAllByChatId(chatId).stream()
                .map(chatMessageResponseMapper::toDto)
                .collect(Collectors.toList()); // Utilisation de Collectors.toList()
    }}

