package com.bootcamp.demo.demo_sb_thymeleaf_coingecko.service;

import java.util.List;
import com.bootcamp.demo.demo_sb_thymeleaf_coingecko.model.dto.CoinDTO;

public interface CoinService {
  List<CoinDTO> getCoins();
}