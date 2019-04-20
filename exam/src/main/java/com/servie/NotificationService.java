package com.servie;

import com.entity.Message;
import com.entity.Notification;
import com.repository.MessageRepository;
import com.repository.NotificationRepository;
import com.vo.NotificationVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Configuration
public class NotificationService {

    @Resource
    private NotificationRepository notificationRepository;

    @Resource
    private MessageRepository messageRepository;

    public List<NotificationVo> getNormalByUserId(Long userId) {
        List<Notification> notificationList = notificationRepository.findByUserId(userId);
        Set<Long> messageIdSet = new HashSet<>();
        for (Notification notification : notificationList) {
            messageIdSet.add(notification.getMessageId());
        }
        if (notificationList == null || notificationList.size() == 0) {
            return null;
        }
        List<Message> messageList = messageRepository.findByIdIn(messageIdSet);
        if (messageList == null || messageList.size() == 0) {
            return null;
        }
        List<NotificationVo> notificationVoList = new ArrayList<>();
        for (Message message : messageList) {
            NotificationVo notificationVo = new NotificationVo();
            notificationVo.setTitle(message.getTitle());
            notificationVo.setTime(message.getTime());
            notificationVoList.add(notificationVo);
        }
        return notificationVoList;
    }
}