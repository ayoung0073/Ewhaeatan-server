package com.mayko.ewhaplate.service;

import com.mayko.ewhaplate.dto.request.FoodRandomRequestDto;
import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.repository.FoodRepository;
import com.mayko.ewhaplate.repository.MenuRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @MockBean
    private FoodRepository foodRepository;

    @MockBean
    private MenuRepository menuRepository;

    Food food;
    FoodRandomRequestDto foodRequestDto;

    @Before
    public void setUp(){
        FoodRequestDto dto = new FoodRequestDto("낭만식탁", "일식", "ㅁㄹ", "030", "정문", "");
        FoodRequestDto dto2 = new FoodRequestDto("서브웨이", "일식", "ㅁㄹ", "030", "정문", "");
        FoodRequestDto dto3 = new FoodRequestDto("마린파스타", "일식", "ㅁㄹ", "030", "정문", "");
        FoodRequestDto dto4 = new FoodRequestDto("스탠바이키친", "일식", "ㅁㄹ", "030", "정문", "");

        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food(dto));
        foodList.add(new Food(dto2));
        foodList.add(new Food(dto3));
        foodList.add(new Food(dto4));

        foodRepository.saveAll(foodList);
    }

    @Test
    public void 랜덤_테스트_성공케이스(){

        List<String> list = new ArrayList<>();
        list.add("일식");
        foodRequestDto = new FoodRandomRequestDto(list, "정문");
        Food food = foodService.getRandomFood(foodRequestDto);
        assertNotNull(food);
    }
}