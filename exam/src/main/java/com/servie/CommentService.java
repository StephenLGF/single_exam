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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Configuration
public class CommentService {

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private NewsRepository newsRepository;

    public List<CommentVo> getCommentByNewsId(Long newsId) {
        List<Comment> commentList = commentRepository.findByNewsId(newsId);
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
}