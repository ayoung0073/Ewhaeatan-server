package com.mayko.ewhaplate.controller.api;

import com.mayko.ewhaplate.dto.request.FoodRandomRequestDto;
import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import com.mayko.ewhaplate.dto.request.FoodWantRequestDto;
import com.mayko.ewhaplate.dto.response.SuccessDto;
import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.service.FoodService;
import com.mayko.ewhaplate.utils.GoogleImgSearch;
import com.mayko.ewhaplate.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;
    private final GoogleImgSearch googleImgSearch;
    private final MenuService menuService;

    // 추천 맛집 등록
    @PostMapping("/register")
    public SuccessDto register(@RequestBody FoodRequestDto requestDto) throws Exception {
        Map<String, String> map = googleImgSearch.getUrl(requestDto.getName()); // 음식점이름 이미지 search한 후
        requestDto.setImageUrl(map.get("imageUrl")); // 이미지 URL 저장
        requestDto.setUrl(map.get("url")); // 이미지 URL 저장

        foodService.register(requestDto);

        return new SuccessDto(true);
    }

    // 카테고리 포함 x, 이화 장소가 ewhaType인 맛집 중 랜덤으로 뽑기
    @PostMapping("/random")
    public Food getRandomFood(@RequestBody FoodRandomRequestDto requestDto) {
        return foodService.getRandomFood(requestDto);
    }

    // 카테고리 포함 O, 이화 장소가 ewhaType인 맛집 리스트
    @PostMapping("/list")
    public List<Food> getList(@RequestBody FoodWantRequestDto requestDto){
        List<Food> list = foodService.getFoodList(requestDto);
        if(list.size() == 0) throw new IllegalArgumentException("해당 맛집이 없습니다");
        else return list;
    }

    // 이화 주변 맛집 전체 리스트 GET
    @GetMapping("/list/all")
    public List<Food> getAllList(){
        return foodService.getAllFood();
    }

    @GetMapping("/url") // 해당 음식점 imageURL 찾기
    public Map<String, String> getUrl(@RequestParam String name) {
        return googleImgSearch.getUrl(name);
    }

    // name에 이대, 신촌 포함하기
    @GetMapping("/addMenu") // 메뉴만 추가
    public String addMenu(@RequestParam String name) throws IOException {
        return menuService.addMenu(name);
    }

    @GetMapping("/{foodId}") // 맛집 자세히 보기
    public Food getFood(@PathVariable("foodId") Long foodId) {
        return foodService.getFood(foodId);
    }

    @PutMapping("/update/{foodId}")
    public SuccessDto updateFood(@PathVariable("foodId") Long foodId, @RequestBody FoodRequestDto requestDto){
        foodService.updateFood(foodId, requestDto);
        return new SuccessDto(true);
    }


    @DeleteMapping("/delete/{foodId}")
    public SuccessDto deleteFood(@PathVariable("foodId") Long foodId) {
        foodService.deleteFood(foodId);
        return new SuccessDto(true);
    }

}

