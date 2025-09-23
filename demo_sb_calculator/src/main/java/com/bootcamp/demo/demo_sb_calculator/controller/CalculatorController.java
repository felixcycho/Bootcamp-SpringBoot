package com.bootcamp.demo.demo_sb_calculator.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.bootcamp.demo.demo_sb_calculator.DemoSbCalculatorApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


// ! browser -> web request ->
@Controller
@ResponseBody
public class CalculatorController {

    private final DemoSbCalculatorApplication demoSbCalculatorApplication;

    CalculatorController(DemoSbCalculatorApplication demoSbCalculatorApplication) {
        this.demoSbCalculatorApplication = demoSbCalculatorApplication;
    }
  @GetMapping("/sum/{x}/{y}")
  public Integer sum(@PathVariable Integer x, @PathVariable Integer y) {
    return x + y;
  };

  @GetMapping("/subtract/{x}/{y}")
  public Integer substract(@PathVariable Integer x, @PathVariable Integer y) {
    return x - y;
  }

  @GetMapping("/multiple/{x}/{y}")
  public Double multiple(@PathVariable(value = "x") Double salary, 
    @PathVariable(value = "y") Double months) {
    return BigDecimal.valueOf(salary)
           .multiply(BigDecimal.valueOf(months))
           .setScale(2)
           .doubleValue();
  }

  @GetMapping("/average/candy/{x}/{y}")
  public Double divide(@PathVariable(value = "x") Double candyCount, 
    @PathVariable(value = "y") Double personCount) {
    return BigDecimal.valueOf(candyCount)
           .divide(BigDecimal.valueOf(personCount), 2, RoundingMode.HALF_UP)
           .doubleValue();
  }

  @GetMapping("/stringsum/{x}/{y}")
  public Double stringsum(@PathVariable String x, @PathVariable String y) {
    // x can be anything
    // how to convert x to number?
    double x1, y1;
    try {
      // double x1 = Double.valueOf(x);
      x1 = Double.valueOf(x);
    } catch (NumberFormatException e) {
      return -100_000_000.0;
    }

    try {
      // double y1 = Double.valueOf(y);
      y1 = Double.valueOf(y);
    } catch (NumberFormatException e) {
      return -100_000_000.0;
    }
    // return x1 + y1;       // Compile error, x1 and y1 are declared in try.
    // Solution:     declare x1 and y1 outside try.
    return x1 + y1;
  }

  // Example: Combine sum/subtract/multiply/divide into one API
  // User input: sum/subtract/multiply/divide, x, y
  



}
