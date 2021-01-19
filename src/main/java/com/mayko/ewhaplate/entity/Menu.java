package com.mayko.ewhaplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "foodId")
    @JsonIgnore
    private Food food;

    private String menuName;

    private int price;

    public Menu(Food food, String menuName, int price) {
        this.food = food;
        this.menuName = menuName;
        this.price = price;
    }

    public void updateMenu(String menuName, int price){
        this.menuName = menuName;
        this.price = price;
    }
}
