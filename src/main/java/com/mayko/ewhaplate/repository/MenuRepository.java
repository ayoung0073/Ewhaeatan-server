package com.mayko.ewhaplate.repository;

import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByFoodId(Long foodId);
}
