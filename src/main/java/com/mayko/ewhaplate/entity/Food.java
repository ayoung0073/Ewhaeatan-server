package com.mayko.ewhaplate.entity;

import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String category; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인 됨

    @Column(nullable = false)
    private String address; // 조회수

    @Column(nullable = false)
    private String phone; // DB는 오브젝트 저장할 수 없음, FK, 자바는 오브젝트 저장 가능

    @Column(nullable = false)
    private String ewhaType;

    public Food(FoodRequestDto dto){
        this.address = dto.getAddress();
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.ewhaType = dto.getEwhaType();
        this.category = dto.getCategory();
    }
}
