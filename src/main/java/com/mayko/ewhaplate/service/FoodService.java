package com.mayko.ewhaplate.service;

import com.mayko.ewhaplate.dto.request.FoodRandomRequestDto;
import com.mayko.ewhaplate.dto.response.ExceptionDto;
import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService{
    private final FoodRepository foodRepository;

    // 랜덤 맛집 뽑기
    @Transactional(readOnly = true)
    public Food getRandomFood(FoodRandomRequestDto requestDto) {
        List<Food> foodList = foodRepository.findAllByCategoryIsNotInAndEwhaType(requestDto.getCategories(), requestDto.getEwhaType());
        // 난수를 foodList 개수로 나눈 나머지 -> 랜덤 음식
        if(foodList.size() == 0) throw new IllegalArgumentException("해당 맛집이 없습니다");
        else {
            int random = (int) ((Math.random() * 10000) % (foodList.size()));
            return foodList.get(random);
        }
    }

    // 조건에 해당하는 맛집 리스트
    @Transactional(readOnly = true)
    public List<Food> getFoodList(FoodRandomRequestDto requestDto){
        return foodRepository.findAllByCategoryIsNotInAndEwhaType(requestDto.getCategories(), requestDto.getEwhaType());
    }

    // 맛집 등록
    @Transactional
    public void register(Food food){
        foodRepository.save(food);
    }

    @Transactional(readOnly = true)
    public List<Food> getAllFood(){
        return foodRepository.findAll();
    }

}
