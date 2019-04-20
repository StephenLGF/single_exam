package com.servie;

import com.entity.Collection;
import com.entity.Exam;
import com.entity.Paper;
import com.entity.Quiz;
import com.repository.CollectionRepository;
import com.repository.ExamRepository;
import com.repository.PaperRepository;
import com.repository.QuizRepository;
import com.vo.CollectionVo;
import com.vo.ExamVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

@Service
@Configuration
public class ExamService {

    @Resource
    private ExamRepository examRepository;

    @Resource
    private PaperRepository paperRepository;

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
        for (Exam exam : examList) {
            ExamVo examVo = new ExamVo();
            examVo.setPaperId(exam.getPaperId());
            examVo.setEndTime(exam.getEndTime());
            examVo.setStartTime(exam.getStartTime());
            examVo.setResult(exam.getResult());
            examVo.setState(exam.getState());
            for (Paper paper : paperList) {
                if (Objects.equals(paper.getId(), exam.getPaperId())) {
                    examVo.setCreateTime(paper.getTime());
                    examVo.setCreator(paper.getCreator());
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