package com.servie;

import com.entity.Quiz;
import com.entity.WrongQuiz;
import com.repository.QuizRepository;
import com.repository.WrongQuizRepository;
import com.vo.QuizVo;
import com.vo.WrongQuizVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Configuration
public class WrongQuizService {

    @Resource
    private WrongQuizRepository wrongQuizRepository;


    @Resource
    private QuizRepository quizRepository;

    public List<WrongQuizVo> getWrongQuizByUserId(Long id) {
        List<WrongQuiz> wrongQuizList = wrongQuizRepository.findWrongQuizByUserId(id);
        if (wrongQuizList == null) {
            return null;
        }
        Set<Long> questionSet = new HashSet<>();
        for (WrongQuiz wrongQuiz : wrongQuizList) {
            questionSet.add(wrongQuiz.getId());
        }
        List<Quiz> quizList = quizRepository.findQuizByIdIn(questionSet);
        if (quizList == null) {
            return null;
        }
        List<WrongQuizVo> wrongQuizVoList = new ArrayList<>();
        for (WrongQuiz wrongQuiz : wrongQuizList) {
            WrongQuizVo wrongQuizVo = new WrongQuizVo();
            wrongQuizVo.setId(wrongQuiz.getId());
            wrongQuizVo.setwAnswer(wrongQuiz.getwAnswer());
            for (Quiz quiz : quizList) {
                if (wrongQuiz.getQuestionId() == quiz.getId()) {
                    wrongQuizVo.setAnswer(quiz.getAnswer());
                    wrongQuizVo.setQuestion(quiz.getQuestion());
                    wrongQuizVo.setSelections(quiz.getSelection());
                    wrongQuizVo.setType(quiz.getType());
                }
            }
            wrongQuizVo.setTime(wrongQuiz.getTime());
            wrongQuizVoList.add(wrongQuizVo);
        }
        return wrongQuizVoList;
    }

    public WrongQuiz addWrongQuiz(Long questionId, Long userId, String wAnswerArray) {
        WrongQuiz wrongQuiz = new WrongQuiz();
        wrongQuiz.setQuestionId(questionId);
        wrongQuiz.setUserId(userId);
        wrongQuiz.setwAnswer(wAnswerArray);
        wrongQuizRepository.save(wrongQuiz);
        return wrongQuiz;
    }
}