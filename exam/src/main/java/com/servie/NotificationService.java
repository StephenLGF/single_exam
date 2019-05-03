package com.servie;

import com.entity.Notification;
import com.repository.NotificationRepository;
import com.vo.NotificationVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
            notificationVo.setNewsId(notification.getNewsId());
            notificationVoList.add(notificationVo);
        }
        return notificationVoList;
    }

    public Notification AddNotification(Long newsId, Long userId, String content) {
        Notification notification = new Notification();
        notification.setNewsId(newsId);
        notification.setUserId(userId);
        notification.setTime(new Date(System.currentTimeMillis()));
        notification.setContent(content);
        notificationRepository.save(notification);
        return notification;
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