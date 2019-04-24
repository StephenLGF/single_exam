package com.servie;

import com.entity.Exam;
import com.entity.Paper;
import com.entity.User;
import com.repository.ExamRepository;
import com.repository.PaperRepository;
import com.repository.UserRepository;
import com.vo.ExamVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Configuration
public class ExamService {

    @Resource
    private ExamRepository examRepository;

    @Resource
    private PaperRepository paperRepository;

    @Resource
    private UserRepository userRepository;

    public List<ExamVo> getExamsByUserId(Long userId) {
        List<Exam> examList = examRepository.findByUserId(userId);
        if (examList == null || examList.size() == 0) {
            return null;
        }
        Set<Long> paperIdSet = new HashSet<>();
        for (Exam exam : examList) {
            paperIdSet.add(exam.getPaperId());
        }
        List<Paper> paperList = paperRepository.findByIdIn(paperIdSet);
        if (paperList == null || paperList.size() == 0) {
            return null;
        }
        List<ExamVo> examVoList = new ArrayList<>();
        Set<Long> creatorSet = new HashSet<>();
        for (Paper paper : paperList) {
            creatorSet.add(paper.getCreator());
        }
        List<User> userList = userRepository.findByIdIn(creatorSet);
        for (Exam exam : examList) {
            ExamVo examVo = new ExamVo();
            examVo.setPaperId(exam.getPaperId());
            examVo.setEndTime(exam.getEndTime());
            examVo.setStartTime(exam.getStartTime());
            examVo.setResult(exam.getResult());
            examVo.setState(exam.getState());
            for (Paper paper : paperList) {
                if (Objects.equals(paper.getId(), exam.getPaperId())) {
                    for (User user : userList) {
                        if (user.getId().equals(paper.getCreator())) {
                            examVo.setCreator(user.getName());
                            break;
                        }
                    }
                    examVo.setCreateTime(paper.getTime());
                    examVo.setName(paper.getName());
                    examVo.setQuestions(paper.getQuestions());
                    break;
                }
            }
            examVoList.add(examVo);
        }
        return examVoList;
    }
}