package com.mayko.ewhaplate.repository;
import com.mayko.ewhaplate.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    // 중복 음식점 확인
    Optional<Food> findByName(String name);

    // random 메서드
    @Query(value = "select * from food where category not in (?1) and ewhaType = (?2)", nativeQuery = true)
    List<Food> findAllByCategoryNotInAndEwhaType(Collection<String> categories, String ewhaType);

    // category, ewhaType 요청 둘 다 있는 경우
    @Query(value = "select * from food where category in (?1) and ewhaType in (?2)", nativeQuery = true)
    List<Food> findAllByCategoryInAndEwhaTypeIn(Collection<String> categories, Collection<String> ewhaType);

    // category만 요청 있는 경우
    @Query(value = "select * from food where category in (?1)", nativeQuery = true)
    List<Food> findAllByCategoryIn(Collection<String> categories);

    // ewhaType만 요청 있는 경우
    @Query(value = "select * from food where ewhaType in (?1)", nativeQuery = true)
    List<Food> findAllByEwhaTypeIn(Collection<String> ewhaTypes);



    Optional<Food> findDistinctByName(String name);

}
