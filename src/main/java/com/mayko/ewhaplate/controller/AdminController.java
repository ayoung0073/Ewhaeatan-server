package com.mayko.ewhaplate.controller;

import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import com.mayko.ewhaplate.repository.FoodRepository;
import com.mayko.ewhaplate.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final FoodService foodService;

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("list", foodService.getAllFood());
        return "admin";
    }

    @GetMapping("/getFood/{foodId}")
    public String getOne(@PathVariable("foodId") Long foodId, Model model){
        model.addAttribute("food", foodService.getFood(foodId));
        return "one";
    }

}
