package com.mayko.ewhaplate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FoodRandomRequestDto {

    private String category; // 제외할 카테고리

    private String ewhaType;
}
