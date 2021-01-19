package com.mayko.ewhaplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column
    private String imageUrl;

    @OneToOne(mappedBy = "food", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Menu menu;

    @Column
    private String url;

    public Food(FoodRequestDto dto){
        this.address = dto.getAddress();
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.ewhaType = dto.getEwhaType();
        this.category = dto.getCategory();
        this.imageUrl = dto.getImageUrl();
        this.url = dto.getUrl();
    }

    public Food(String address, String name, String phone, String category, String ewhaType, String imageUrl) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.phone = phone;
        this.ewhaType = ewhaType;
        this.imageUrl = imageUrl;
    }

    public void updateFood(FoodRequestDto dto){
        this.name = dto.getName();
        this.category = dto.getCategory();
        this.ewhaType = dto.getEwhaType();
        this.url = dto.getUrl();
        this.imageUrl = dto.getImageUrl();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
    }
}
