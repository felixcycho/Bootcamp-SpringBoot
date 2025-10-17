package com.bootcamp.demo.demo_sb_thymeleaf_coingecko.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonIgnoreProperties   (ignoreUnknown = true)
public class CoinDto {
  private String id;
  private String symbol;
  private String name;
  private String image;
  @JsonProperty("current_price")
  private Double currentPrice;
  @JsonProperty("market_cap")
  private Long marketCap;
  @JsonProperty("price_change_percentage_24h")
  private Double priceChangePercent24h;
  @JsonProperty("last_updated")
  private LocalDateTime lastUpdated;
}