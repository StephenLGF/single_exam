package com.servie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.entity.News;
import com.entity.Provider;
import com.repository.NewsRepository;
import com.repository.ProviderRepository;
import com.vo.NewsVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

@Service
@Configuration
public class NewsService {

    @Resource
    private NewsRepository newsRepository = null;

    @Resource
    private ProviderRepository providerRepository = null;

    public List<NewsVo> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        if (newsList == null || newsList.size() == 0) {
            return null;
        }
        return translate(newsList);
    }

    public List<NewsVo> getAllWords() {
        List<News> newsList = newsRepository.findByType(0);
        if (newsList == null || newsList.size() == 0) {
            return null;
        }
        return translate(newsList);
    }

    public List<NewsVo> getAllPics() {
        List<News> newsList = newsRepository.findByType(1);
        if (newsList == null || newsList.size() == 0) {
            return null;
        }
        return translate(newsList);
    }

    public List<NewsVo> getAllVideos() {
        List<News> newsList = newsRepository.findByType(2);
        if (newsList == null || newsList.size() == 0) {
            return null;
        }
        return translate(newsList);
    }

    public List<NewsVo> translate(List<News> newsList) {

        List<NewsVo> newsVoList = new ArrayList<>();
        Set<Long> providerIdSet = new HashSet<>();
        for (News news : newsList) {
            providerIdSet.add(news.getProviderId());
        }
        List<Provider> providerList = providerRepository.findByIdIn(providerIdSet);

        for (News news : newsList) {
            if (news.getDeleted() > 0) {
                continue;
            }
            NewsVo newsVo = new NewsVo();
            newsVo.setNewsId(news.getId());
            newsVo.setTitle(news.getTitle());
            newsVo.setCreateTime(news.getTime());
            for (Provider provider : providerList) {
                if (Objects.equals(provider.getId(), news.getProviderId())) {
                    newsVo.setProvider(provider.getName());
                    break;
                }
            }
            newsVo.setType(news.getType());
            newsVoList.add(newsVo);
        }
        return newsVoList;
    }

    public JSONArray getNewsById(Long newsId) {
        News news = newsRepository.findById(newsId);
        if (news == null) {
            return null;
        }
        JSONArray jsonArray = JSON.parseArray(news.getContent());
        return jsonArray;
    }

    public News createNews(String title, String contents, Integer type) {
        News news = new News();
        news.setTitle(title);
        news.setProviderId(1L);
        news.setContent(contents);
        news.setType(type);
        news.setTime(new Date(System.currentTimeMillis()));
        news.setDeleted(0);
        return newsRepository.save(news);
    }

    public News deleteNewsById(Long newsId) {
        News news = newsRepository.findById(newsId);
        if (news == null) {
            return null;
        }
        news.setDeleted(1);
        return newsRepository.save(news);
    }
}