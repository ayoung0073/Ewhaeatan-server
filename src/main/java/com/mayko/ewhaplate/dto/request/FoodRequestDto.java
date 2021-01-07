package com.mayko.ewhaplate.dto.request;

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
}
