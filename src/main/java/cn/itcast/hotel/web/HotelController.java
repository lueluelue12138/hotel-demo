package cn.itcast.hotel.web;

import cn.itcast.hotel.pojo.ApUserSearch;
import cn.itcast.hotel.pojo.PageResult;
import cn.itcast.hotel.pojo.RequestParams;
import cn.itcast.hotel.service.IHotelSearchService;
import cn.itcast.hotel.service.IHotelService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IHotelSearchService hotelSearchService;

    @PostMapping("list")
    public PageResult search(@RequestBody RequestParams params) {
        return hotelService.search(params);
    }

    @PostMapping("filters")
    public Map<String, List<String>> getFilters(@RequestBody RequestParams params) throws IOException {
        return hotelService.filters(params);
    }

    @PostMapping("getHistory")
    public List<ApUserSearch> getHistory()  {
        return hotelSearchService.getHistory();
    }

    @PostMapping("delHistory")
    public void delHistory(String id)  {
        hotelSearchService.delHistory(id);
    }

    @GetMapping("suggestion")
    public List<String> getSuggestions(@RequestParam("key") String prefix) throws IOException {
        return hotelService.getSuggestions(prefix);
    }
}
