package com.servie;

import com.entity.Collection;
import com.entity.Quiz;
import com.repository.CollectionRepository;
import com.repository.QuizRepository;
import com.vo.CollectionVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Configuration
public class CollectionService {

    @Resource
    private CollectionRepository collectionRepository;


    @Resource
    private QuizRepository quizRepository;

    public List<CollectionVo> getCollectionByUserId(Long userId) {
        List<Collection> collectionList = collectionRepository.findByUserId(userId);
        if (collectionList == null) {
            return null;
        }
        Set<Long> questionSet = new HashSet<>();
        for (Collection collection : collectionList) {
            questionSet.add(collection.getQuestionId());
        }
        List<Quiz> quizList = quizRepository.findQuizByIdIn(questionSet);
        if (quizList == null) {
            return null;
        }
        List<CollectionVo> collectionVoList = new ArrayList<>();
        for (Collection collection : collectionList) {
            CollectionVo collectionVo = new CollectionVo();
            collectionVo.setId(collection.getId());
            for (Quiz quiz : quizList) {
                if (collection.getQuestionId() == quiz.getId()) {
                    collectionVo.setAnswer(quiz.getAnswer());
                    collectionVo.setQuestion(quiz.getQuestion());
                    collectionVo.setSelections(quiz.getSelection());
                    collectionVo.setType(quiz.getType());
                }
            }
            collectionVo.setTime(collection.getTime());
            collectionVoList.add(collectionVo);
        }
        return collectionVoList;
    }

    public Collection addCollection(Long questionId, Long userId) {
        Collection collection = new Collection();
        collection.setQuestionId(questionId);
        collection.setUserId(userId);
        collection.setTime(new Date(System.currentTimeMillis()));
        collectionRepository.save(collection);
        return collection;
    }
}