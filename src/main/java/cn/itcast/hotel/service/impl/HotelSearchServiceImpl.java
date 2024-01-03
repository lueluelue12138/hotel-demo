package cn.itcast.hotel.service.impl;

import cn.itcast.hotel.pojo.ApUserSearch;
import cn.itcast.hotel.service.IHotelSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class HotelSearchServiceImpl implements IHotelSearchService {

    @Autowired
    MongoTemplate mongoTemplate;
    /**
     * 保存用户搜索历史记录
     *
     * @param keyword
     * @param userId
     */
    @Override
    @Async
    public void insert(String keyword, Integer userId) {
        if (StringUtils.isEmpty(keyword)){
            return;
        }
        //查询当前用户的搜索关键词
        Query query = Query.query(Criteria.where("userId").is(userId).and("keyword").is(keyword));
        ApUserSearch apUserSearch = mongoTemplate.findOne(query, ApUserSearch.class);
        //存在 更新创建时间
        if (apUserSearch!=null){
            apUserSearch.setCreatedTime(new Date());
            mongoTemplate.save(apUserSearch);
            return;
        }

        //不存在 判断当前历史记录总数是否超过10
        apUserSearch = new ApUserSearch();
        apUserSearch.setUserId(userId);
        apUserSearch.setKeyword(keyword);
        apUserSearch.setCreatedTime(new Date());
        //直接替换最后一个记录
        List<ApUserSearch> apUserSearches = mongoTemplate.find(
                Query.query(Criteria.where("userId").is(userId))
                        .with(Sort.by(Sort.Direction.DESC,"createTime")),
                ApUserSearch.class);
        if (apUserSearches==null||apUserSearches.size()<10){
            mongoTemplate.save(apUserSearch);
        }else {
            ApUserSearch apUserSearchLast = apUserSearches.get(apUserSearches.size() - 1);
            mongoTemplate.findAndReplace(Query.query(Criteria.where("id").is(apUserSearchLast.getId())),apUserSearch);
        }

    }
}
