package com.mayko.ewhaplate.api;

import com.mayko.ewhaplate.dto.request.FoodRandomRequestDto;
import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.repository.FoodRepository;
import com.mayko.ewhaplate.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;

    // 추천 맛집 등록
    @PostMapping("/register")
    public Food register(@RequestBody FoodRequestDto requestDto){
        Food food = new Food(requestDto);
        foodService.register(food);
        return food;
    }

    // 카테고리 포함 x, 이화 장소가 ewhaType인 맛집 중 랜덤으로 뽑기
    @GetMapping("/random")
    public Food getRandomFood(@RequestParam HashMap<String, String> map){
        FoodRandomRequestDto requestDto = new FoodRandomRequestDto(map.get("category"), map.get("ewhaType"));
        return foodService.getRandomFood(requestDto);
    }

    // 카테고리 포함 x, 이화 장소가 ewhaType인 맛집 리스트
    @GetMapping("/list")
    public List<Food> getList(@RequestParam HashMap<String, String> map){
        FoodRandomRequestDto requestDto = new FoodRandomRequestDto(map.get("category"), map.get("ewhaType"));
        return foodService.getFoodList(requestDto);
    }

    // 이화 주변 맛집 전체 리스트 GET
    @GetMapping("/list/all")
    public List<Food> getAllList(){
        return foodRepository.findAll();
    }

}
