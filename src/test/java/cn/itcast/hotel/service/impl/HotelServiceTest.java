package cn.itcast.hotel.service.impl;

import cn.itcast.hotel.pojo.RequestParams;
import cn.itcast.hotel.service.IHotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HotelServiceTest {

    @Autowired
    private IHotelService hotelService;

    @Test
    void filters() throws IOException {
        RequestParams params = new RequestParams();
        Map<String, List<String>> filters = hotelService.filters(params);
        System.out.println(filters);
    }
}