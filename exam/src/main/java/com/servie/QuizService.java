package com.servie;

import com.repository.QuizRepository;
import com.entity.Quiz;
import com.vo.QuizVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Configuration
public class QuizService {

    @Resource
    private QuizRepository quizRepository;

    public QuizVo getQuizById(Long id) {
        QuizVo quizVo = new QuizVo();
        Quiz quiz = quizRepository.findQuizById(id);
        if (quiz == null) {
            return null;
        }
        quizVo.setId(quiz.getId());
        quizVo.setAnswer(quiz.getAnswer());
        quizVo.setSelections(quiz.getSelection());
        quizVo.setQuestion(quiz.getQuestion());
        quizVo.setType(quiz.getType());
        return quizVo;
    }

    public long getQuizNum() {
        return quizRepository.count();
    }
}