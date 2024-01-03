package cn.itcast.hotel.service;

import cn.itcast.hotel.pojo.ApUserSearch;

import java.util.List;

public interface IHotelSearchService {

    /**
     * 保存用户搜索历史记录
     * @param keyword
     * @param userId
     */
    public void insert(String keyword,Integer userId);

    List<ApUserSearch> getHistory();


    void delHistory(String id);

}
