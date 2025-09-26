package com.bootcamp.demo.demo_sb_calculator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_calculator.model.ShoppingMall;
import com.bootcamp.demo.demo_sb_calculator.model.ShoppingMall.Cinema;
import com.bootcamp.demo.demo_sb_calculator.model.ShoppingMall.Cinema.Film;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ShoppingMallController {
  
  @GetMapping("/shoppingmall")
  public ShoppingMall getShoppingMall() {
    Film film1 = Film.builder()
                 .name("Film A")
                 .releaseDate(LocalDate.of(2022, 1, 2))
                 .build();

    Film film2 = Film.builder()
                 .name("Film B")
                 .releaseDate(LocalDate.of(2025, 11 ,12))
                 .build();
    
    Cinema c1 = Cinema.builder()
                .name("Cinema A")
                .openedDate(LocalDate.of(2000, 1, 2))
                .releasedFilms(List.of(film1, film2))
                .build();
    
    List<String> shopCategories = List.of("Food", "Sport", "Clothing");

    return ShoppingMall.builder()
           .name("K11")
           .area(19000)
           .cinema(c1)
           .shopCategory(shopCategories)
           .build();
  }
  
}
