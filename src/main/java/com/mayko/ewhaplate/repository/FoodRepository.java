package com.mayko.ewhaplate.repository;

import com.mayko.ewhaplate.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByCategoryIsNotContainingAndEwhaType(String category, String ewhaType);
}
