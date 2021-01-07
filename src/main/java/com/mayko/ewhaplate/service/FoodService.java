package com.mayko.ewhaplate.service;

import com.mayko.ewhaplate.dto.request.FoodRandomRequestDto;
import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    // 랜덤 맛집 뽑기
    public Food getRandomFood(FoodRandomRequestDto requestDto){
        List<Food> foodList = foodRepository.findAllByCategoryIsNotContainingAndEwhaType(requestDto.getCategory(), requestDto.getEwhaType());
        // 난수를 foodList 개수로 나눈 나머지 -> 랜덤 음식
        int random = (int)((Math.random()*10000)%(foodList.size()));
        //System.out.println(foodList.size() + " " + random);

        return foodList.get(random);
    }

    // 조건에 해당하는 맛집 리스트
    public List<Food> getFoodList(FoodRandomRequestDto requestDto){
        return foodRepository.findAllByCategoryIsNotContainingAndEwhaType(requestDto.getCategory(), requestDto.getEwhaType());
    }

    // 맛집 등록
    public void register(Food food){
        foodRepository.save(food);
    }
}
