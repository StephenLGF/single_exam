package com.servie;

import com.entity.Collection;
import com.entity.News;
import com.entity.Quiz;
import com.entity.VisitList;
import com.repository.NewsRepository;
import com.repository.VisitListRepository;
import com.vo.CollectionVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

@Service
@Configuration
public class VisitListService {

    @Resource
    private VisitListRepository visitListRepository;

    @Resource
    private NewsRepository newsRepository;

    public List<News> getVisitListByUserId(Long userId) {
        List<VisitList> visitListList = visitListRepository.findByUserId(userId);
        if (visitListList == null) {
            return null;
        }
        Set<Long> newsIdSet = new HashSet<>();
        for (VisitList visitList : visitListList) {
            newsIdSet.add(visitList.getNewsId());
        }
        List<News> newsList = newsRepository.findByIdIn(newsIdSet);
        if (newsList == null) {
            return null;
        }
        return newsList;
    }

    public VisitList addVisitHistory(Long newsId, Long userId) {
        VisitList visitList = new VisitList();
        visitList.setNewsId(newsId);
        visitList.setUserId(userId);
        visitList.setTime(new Date(System.currentTimeMillis()));
        return visitListRepository.save(visitList);
    }
}