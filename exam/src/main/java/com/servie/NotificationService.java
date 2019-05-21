package com.servie;

import com.entity.Notification;
import com.repository.NotificationRepository;
import com.vo.NotificationVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Configuration
public class NotificationService {

    @Resource
    private NotificationRepository notificationRepository;

    public List<NotificationVo> getNotificationByUserId(Long userId) {
        List<NotificationVo> notificationVoList = new ArrayList<>();
        List<Notification> notificationList = notificationRepository.findByUserId(userId);
        if (notificationList == null || notificationList.size() == 0) {
            return notificationVoList;
        }
        for (Notification notification : notificationList) {
            if (notification.getDeleted() > 0) {
                continue;
            }
            NotificationVo notificationVo = new NotificationVo();
            notificationVo.setId(notification.getId());
            notificationVo.setTime(notification.getTime());
            notificationVo.setContent(notification.getContent());
            notificationVoList.add(notificationVo);
        }
        return notificationVoList;
    }

    public void AddNotification(Set<Long> userIds, String content) {
        List<Notification> notificationList = new ArrayList<>();
        Date dateNow = new Date(System.currentTimeMillis());
        for (Long userId : userIds) {
            Notification notification = new Notification();
            notification.setUserId(userId);
            notification.setTime(dateNow);
            notification.setContent(content);
            notification.setDeleted(0);
            notificationList.add(notification);
        }
        notificationRepository.save(notificationList);
    }

    public Notification deletaNotification(Long userId, Long noticeId) {
        Notification notification = notificationRepository.findById(noticeId);
        if (notification == null || !Objects.equals(notification.getUserId(), userId)) {
            return null;
        }
        notification.setDeleted(1);
        notificationRepository.save(notification);
        return notification;
    }

}