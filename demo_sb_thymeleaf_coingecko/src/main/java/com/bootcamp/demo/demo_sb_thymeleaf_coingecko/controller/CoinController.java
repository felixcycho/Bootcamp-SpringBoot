package com.bootcamp.demo.demo_sb_thymeleaf_coingecko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.demo_sb_thymeleaf_coingecko.model.dto.CoinDTO;


// @RestController
@Controller
public class CoinController {
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/coins")
  public String getCoins(Model model) {
    String url =
        "https://api.coingecko.com/api/v3/coins/markets?ids=bitcoin,ethereum,tether&vs_currency=usd";
    CoinDTO[] coins = this.restTemplate.getForObject(url, CoinDTO[].class);
    model.addAttribute("coinList", coins);
    return "coin"; // return coin.html
    // return Arrays.asList(coins);
  }

}
