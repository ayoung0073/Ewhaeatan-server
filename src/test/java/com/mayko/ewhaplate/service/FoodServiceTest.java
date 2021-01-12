package com.mayko.ewhaplate.service;

import com.mayko.ewhaplate.dto.request.FoodRandomRequestDto;
import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.repository.FoodRepository;
import com.mayko.ewhaplate.repository.MenuRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    //@MockBean
    @Autowired
    private FoodRepository foodRepository;

//    @MockBean
//    private MenuRepository menuRepository;

//    @Before
//    public void setUp(){
//    }

    @Test // org.junit 이 아니라 org.junit.jupiter.api.Test; 를 import 해야 test 통과
    @Transactional
    public void 랜덤_테스트_성공케이스(){

//        FoodRequestDto dto = new FoodRequestDto("낭만식탁", "일식", "ㅁㄹ", "030", "정문", "");
//        FoodRequestDto dto2 = new FoodRequestDto("서브웨이", "중식", "ㅁㄹ", "030", "정문", "");
//        FoodRequestDto dto3 = new FoodRequestDto("마린파스타", "일식", "ㅁㄹ", "030", "후문", "");
//        FoodRequestDto dto4 = new FoodRequestDto("스탠바이키친", "일식", "ㅁㄹ", "030", "신촌", "");
//
//        List<Food> foodList = new ArrayList<>();
//        foodList.add(new Food(dto));
//        foodList.add(new Food(dto2));
//        foodList.add(new Food(dto3));
//        foodList.add(new Food(dto4));
//
//        foodRepository.saveAll(foodList);
        List<String> list = new ArrayList<>();

        list.add("일식");

        FoodRandomRequestDto foodRequestDto = new FoodRandomRequestDto(list, "정문");

        Food food = foodService.getRandomFood(foodRequestDto);
        //assertThat("정문", is(food.getEwhaType()));
        assertEquals("정문", food.getEwhaType());
        //assertNotNull(food);
    }

//    @Rule
//    public final ExpectedException exception = ExpectedException.none();

    @Test
    @Transactional
    public void 랜덤_테스트_예외케이스(){
        List<String> list = new ArrayList<>();

        list.add("일식");

        FoodRandomRequestDto foodRequestDto = new FoodRandomRequestDto(list, "정문");

        Food food = foodService.getRandomFood(foodRequestDto);
        //exception.expect(IllegalArgumentException.class);
        assertNotEquals("후문", food.getEwhaType());
    }
}