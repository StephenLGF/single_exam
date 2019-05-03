package com.servie;

import com.entity.News;
import com.entity.Provider;
import com.entity.VisitList;
import com.repository.NewsRepository;
import com.repository.ProviderRepository;
import com.repository.VisitListRepository;
import com.vo.VisitListVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Configuration
public class VisitListService {

    @Resource
    private VisitListRepository visitListRepository;

    @Resource
    private NewsRepository newsRepository;

    @Resource
    private ProviderRepository providerRepository;

    public List<VisitListVo> getVisitListByUserId(Long userId) {
        List<VisitList> visitListList = visitListRepository.findByUserId(userId);
        if (visitListList == null) {
            return null;
        }
        Set<Long> newsIdSet = new HashSet<>();
        for (VisitList visitList : visitListList) {
            if (visitList.getDeleted() == 0) {
                newsIdSet.add(visitList.getNewsId());
            }
        }
        List<News> newsList = newsRepository.findByIdIn(newsIdSet);
        Set<Long> providerIdSet = new HashSet<>();
        for (News news : newsList) {
            providerIdSet.add(news.getProviderId());
        }
        List<Provider> providerList = providerRepository.findByIdIn(providerIdSet);
        List<VisitListVo> visitListVoList = new ArrayList<>();
        for (VisitList visitList : visitListList) {
            if (visitList.getDeleted() > 0) {
                continue;
            }
            VisitListVo visitListVo = new VisitListVo();
            visitListVo.setId(visitList.getId());
            visitListVo.setCreateTime(visitList.getTime());
            visitListVo.setNewsId(visitList.getNewsId());
            for (News news1 : newsList) {
                if (news1.getId().equals(visitList.getNewsId())) {
                    visitListVo.setTitle(news1.getTitle());
                    visitListVo.setType(news1.getType());
                    for (Provider provider : providerList) {
                        if (Objects.equals(provider.getId(), news1.getProviderId())) {
                            visitListVo.setProvider(provider.getName());
                            break;
                        }
                    }
                    break;
                }
            }
            visitListVoList.add(visitListVo);
        }
        return visitListVoList;
    }

    public VisitList addVisitHistory(Long newsId, Long userId) {
        List<VisitList> visitListList = visitListRepository.findByUserIdAndNewsId(userId, newsId);
        VisitList visitList;
        Date dateNow = new Date(System.currentTimeMillis());
        Long a = dateNow.getTime();
        if (visitListList != null && visitListList.size() > 0 && visitListList.get(0).getTime() != null
                && (a - visitListList.get(0).getTime().getTime()) < 1000 * 30 * 20) {
            visitList = visitListList.get(0);
        } else {
            visitList = new VisitList();
            visitList.setNewsId(newsId);
            visitList.setUserId(userId);
            visitList.setTime(dateNow);
        }
        return visitListRepository.save(visitList);
    }

    public VisitList deleteVisitHistory(Long visitId, Long userId) {
        VisitList visitList = visitListRepository.findById(visitId);
        if (visitList == null || !Objects.equals(visitList.getUserId(), userId)) {
            return null;
        }
        visitList.setDeleted(1);
        visitListRepository.save(visitList);
        return visitList;
    }
}