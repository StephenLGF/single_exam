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
            if (collection.getDeleted() == 0) {
                newsIdSet.add(collection.getNewsId());
            }
        }
        List<News> newsList = newsRepository.findByIdIn(newsIdSet);
        if (newsList == null) {
            return null;
        }
        return newsList;
    }

    public Collection addCollection(Long newsId, Long userId) {
        Collection collection = collectionRepository.findByUserIdAndNewsId(userId, newsId);
        if (collection == null) {
            collection = new Collection();
            collection.setNewsId(newsId);
            collection.setUserId(userId);
            collection.setDeleted(0);
            collection.setTime(new Date(System.currentTimeMillis()));
        } else {
            collection.setDeleted(collection.getDeleted() > 0 ? 0 : 1);
            collection.setTime(new Date(System.currentTimeMillis()));
        }

        collectionRepository.save(collection);
        return collection;
    }

    public List<Collection> getCollectionByNewsId(Long newsId) {
        return collectionRepository.findByNewsId(newsId);
    }
}