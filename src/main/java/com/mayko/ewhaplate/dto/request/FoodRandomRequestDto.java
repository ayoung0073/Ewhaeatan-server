package com.mayko.ewhaplate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class FoodRandomRequestDto {

    private List<String> categories; // 제외할 카테고리

    private String ewhaType;
}
