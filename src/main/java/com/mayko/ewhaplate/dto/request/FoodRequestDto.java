package com.mayko.ewhaplate.dto.request;

import com.mayko.ewhaplate.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FoodRequestDto {

    private String name;

    private String category;

    private String address;

    private String phone;

    private String ewhaType;

    private String imageUrl;

    private String url;

    private String menuName;

    private int price;
}
