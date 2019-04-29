package com.servie;

import com.entity.Collection;
import com.entity.News;
import com.repository.CollectionRepository;
import com.repository.NewsRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

@Service
@Configuration
public class CollectionService {

    @Resource
    private CollectionRepository collectionRepository;

    @Resource
    private NewsRepository newsRepository;


    public List<News> getCollectionByUserId(Long userId) {
        List<Collection> collectionList = collectionRepository.findByUserId(userId);
        if (collectionList == null) {
            return null;
        }
        Set<Long> newsIdSet = new HashSet<>();
        for (Collection collection : collectionList) {
            newsIdSet.add(collection.getNewsId());
        }
        List<News> newsList = newsRepository.findByIdIn(newsIdSet);
        if (newsList == null) {
            return null;
        }
        return newsList;
    }

    public Collection addCollection(Long newsId, Long userId) {
        Collection collection = new Collection();
        collection.setNewsId(newsId);
        collection.setUserId(userId);
        collection.setTime(new Date(System.currentTimeMillis()));
        collectionRepository.save(collection);
        return collection;
    }
}