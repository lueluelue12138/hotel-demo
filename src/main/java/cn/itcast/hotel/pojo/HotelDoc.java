package cn.itcast.hotel.pojo;

import jdk.nashorn.internal.runtime.ListAdapter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class HotelDoc {
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Integer score;
    private String brand;
    private String city;
    private String starName;
    private String business;
    private String location;
    private String pic;
    private Object distance;
    private Boolean isAD;
    private List<String> suggestion;

    public HotelDoc(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.price = hotel.getPrice();
        this.score = hotel.getScore();
        this.brand = hotel.getBrand();
        this.city = hotel.getCity();
        this.starName = hotel.getStarName();
        this.business = hotel.getBusiness();
        this.location = hotel.getLatitude() + ", " + hotel.getLongitude();
        this.pic = hotel.getPic();
        if (this.business.contains("、")) {
            //business有多个值，需要切割
            String[] arr = this.business.split("、");
            this.suggestion = new ArrayList<>();
            this.suggestion.add(this.brand);
            this.suggestion.addAll(Arrays.asList(arr));
        } else if (this.business.contains("/")) {
            //business有多个值，需要切割
            String[] arr = this.business.split("/");
            this.suggestion = new ArrayList<>();
            this.suggestion.add(this.brand);
            this.suggestion.addAll(Arrays.asList(arr));
            
        } else {
            this.suggestion = Arrays.asList(this.brand, this.business);
        }
    }
}
