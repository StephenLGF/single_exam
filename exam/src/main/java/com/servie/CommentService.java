package com.servie;

import com.entity.Comment;
import com.entity.User;
import com.repository.CommentRepository;
import com.repository.NewsRepository;
import com.repository.UserRepository;
import com.vo.CommentVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Configuration
public class CommentService {

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private UserRepository userRepository;

    public List<CommentVo> getCommentByNewsId(Long newsId) {
        List<Comment> commentList = commentRepository.findByNewsId(newsId);
        List<CommentVo> commentVoList = new ArrayList<>();
        if (commentList == null || commentList.size() == 0) {
            return commentVoList;
        }
        Set<Long> userSet = new HashSet<>();
        for (Comment comment : commentList) {
            if (comment.getDeleted() == 0) {
                userSet.add(comment.getUserId());
            }
        }
        List<User> userList = userRepository.findByIdIn(userSet);
        for (Comment comment : commentList) {
            if (comment.getDeleted() > 0) {
                continue;
            }
            CommentVo commentVo = new CommentVo();
            commentVo.setContent(comment.getContent());
            commentVo.setTime(comment.getTime());
            for (User user : userList) {
                if (user.getId().equals(comment.getUserId())) {
                    commentVo.setUserName(user.getName());
                    break;
                }
            }
            commentVoList.add(commentVo);
        }
        return commentVoList;
    }

    public Comment AddComment(Long newsId, Long userId, String content) {
        Comment comment = new Comment();
        comment.setNewsId(newsId);
        comment.setUserId(userId);
        comment.setTime(new Date(System.currentTimeMillis()));
        comment.setContent(content);
        comment.setDeleted(0);
        commentRepository.save(comment);
        return comment;
    }

    public List<CommentVo> getFeedback() {
        List<Comment> commentList = commentRepository.findByNewsId(0L);
        List<CommentVo> commentVoList = new ArrayList<>();
        if (commentList == null || commentList.size() == 0) {
            return commentVoList;
        }
        Set<Long> userSet = new HashSet<>();
        for (Comment comment : commentList) {
            userSet.add(comment.getUserId());
        }
        List<User> userList = userRepository.findByIdIn(userSet);
        for (Comment comment : commentList) {
            if (comment.getDeleted() > 0) {
                continue;
            }
            CommentVo commentVo = new CommentVo();
            commentVo.setId(comment.getId());
            commentVo.setContent(comment.getContent());
            commentVo.setTime(comment.getTime());
            for (User user : userList) {
                if (user.getId().equals(comment.getUserId())) {
                    commentVo.setUserName(user.getName());
                    break;
                }
            }
            commentVoList.add(commentVo);
        }
        return commentVoList;
    }

    public Comment deleteFeedback(Long id) {
        Comment comment = commentRepository.findOne(id);
        if (comment == null) {
            return null;
        }
        comment.setDeleted(1);
        commentRepository.save(comment);
        return comment;
    }
}