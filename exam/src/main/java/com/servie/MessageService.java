package com.servie;

import com.entity.Message;
import com.repository.MessageRepository;
import com.vo.MessageVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Configuration
public class MessageService {

    @Resource
    private MessageRepository messageRepository;

    public MessageVo getMessageInfo(Long messageId) {
        Message message = messageRepository.findById(messageId);
        if (message == null) {
            return null;
        }
        MessageVo messageVo = new MessageVo();
        messageVo.setContent(message.getContent());
        messageVo.setTitle(message.getTitle());
        messageVo.setCreator(message.getCreator());
        messageVo.setTime(message.getTime());
        return messageVo;
    }
}