package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cloudplateform_notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "user_id", columnDefinition = "int(11)")
    private Long userId;

    @Column(name = "message_id", columnDefinition = "int(11)")
    private Long messageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
