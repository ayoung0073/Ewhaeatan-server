package com.mayko.ewhaplate.controller;

import com.mayko.ewhaplate.controller.api.FoodController;
import com.mayko.ewhaplate.service.FoodService;
import com.mayko.ewhaplate.service.MenuService;
import com.mayko.ewhaplate.utils.GoogleImgSearch;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FoodController.class)
public class FoodControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    FoodService foodService;

    @MockBean
    GoogleImgSearch googleImgSearch;

    @MockBean
    MenuService menuService;

    @Test
    public void food_list_all_success_test() throws Exception {
        mvc.perform(get("/list/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
