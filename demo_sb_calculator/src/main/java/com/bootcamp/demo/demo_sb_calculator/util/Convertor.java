package com.bootcamp.demo.demo_sb_calculator.util;

public class Convertor {
  
  public Double convert(String s) {
    try {
      return Double.valueOf(s);
    } catch (NumberFormatException e) {
      return -999_999_999.0;
    }
  }

}
