package com.mayko.ewhaplate.repository;
import com.mayko.ewhaplate.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByCategoryIsNotInAndEwhaType(Collection<String> categories, String ewhaType);

    List<Food> findAllByCategoryIsInAndEwhaType(Collection<String> categories, String ewhaType);

    Optional<Food> findDistinctByName(String name);

}
