package com.bootcamp.demo.demo_sb_thymeleaf_coingecko.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


public interface CoinPageOperation {
  @GetMapping("/coins")
  String getCoins(Model model);
}
