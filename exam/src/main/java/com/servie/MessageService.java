package com.servie;

import com.entity.Message;
import com.entity.User;
import com.repository.MessageRepository;
import com.repository.UserRepository;
import com.vo.MessageVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Configuration
public class MessageService {

    @Resource
    private MessageRepository messageRepository;

    @Resource
    private UserRepository userRepository;

    public MessageVo getMessageInfo(Long messageId) {
        Message message = messageRepository.findById(messageId);
        if (message == null) {
            return null;
        }
        User user = userRepository.findById(message.getCreator());
        MessageVo messageVo = new MessageVo();
        messageVo.setContent(message.getContent());
        messageVo.setTitle(message.getTitle());
        messageVo.setCreator(user.getName());
        messageVo.setTime(message.getTime());
        return messageVo;
    }
}