package com.servie;

import com.entity.News;
import com.entity.Provider;
import com.repository.NewsRepository;
import com.repository.ProviderRepository;
import com.vo.NewsVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<String> getNewsById(Long newsId) {
        News news = newsRepository.findById(newsId);
        List<String> result = new ArrayList<>();
        String[] content = news.getContent().split(",");
        for (String item : content) {
            result.add(item);
        }
        return result;
    }
}