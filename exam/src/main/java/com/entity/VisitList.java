package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visit_list")
public class VisitList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "user_id", columnDefinition = "int(11)")
    private Long userId;

    @Column(name = "news_id", columnDefinition = "int(11)")
    private Long newsId;

    @Column(name = "visited_time", columnDefinition = "datetime")
    private Date time;

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

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
