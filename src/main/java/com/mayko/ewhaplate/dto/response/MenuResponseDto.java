package com.mayko.ewhaplate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MenuResponseDto {
    private String menuName;

    private int price;
}
