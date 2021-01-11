package com.mayko.ewhaplate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class FoodWantRequestDto {

    private List<String> categories; // 원하는 카테고리

    private String ewhaType;
}