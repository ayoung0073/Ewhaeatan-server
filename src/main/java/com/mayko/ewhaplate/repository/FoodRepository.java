package com.mayko.ewhaplate.repository;
import com.mayko.ewhaplate.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    // 중복 음식점 확인
    Optional<Food> findByName(String name);

    // random 메서드
    List<Food> findAllByCategoryNotInAndEwhaType(Collection<String> categories, String ewhaType);

    // category, ewhaType 요청 둘 다 있는 경우
    List<Food> findAllByCategoryInAndEwhaTypeIn(Collection<String> categories, Collection<String> ewhaType);

    // category만 요청 있는 경우
    List<Food> findAllByCategoryIn(Collection<String> categories);

    // ewhaType만 요청 있는 경우
    List<Food> findAllByEwhaTypeIn(Collection<String> ewhaTypes);



    Optional<Food> findDistinctByName(String name);

}
