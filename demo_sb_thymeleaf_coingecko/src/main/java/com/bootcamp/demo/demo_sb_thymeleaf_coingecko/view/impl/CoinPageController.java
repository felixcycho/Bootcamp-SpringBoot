// package com.bootcamp.demo.demo_sb_thymeleaf_coingecko.view.impl;

// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import com.bootcamp.demo.demo_sb_thymeleaf_coingecko.model.dto.CoinDTO;
// import com.bootcamp.demo.demo_sb_thymeleaf_coingecko.service.CoinService;
// import com.bootcamp.demo.demo_sb_thymeleaf_coingecko.view.CoinPageOperation;

// @Controller
// public class CoinPageController implements CoinPageOperation {
//   @Autowired
//   private CoinService coinService;

//   @Override
//   public String getCoins(Model model) {
//     List<CoinDTO> coinList = coinService.getCoins();
//     model.addAttribute("coinList", coinList);
//     return "coins";
//   }
// }
