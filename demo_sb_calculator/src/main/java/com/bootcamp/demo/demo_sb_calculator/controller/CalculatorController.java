package com.bootcamp.demo.demo_sb_calculator.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_calculator.util.Calculators;
import com.bootcamp.demo.demo_sb_calculator.util.Convertor;


// ! browser -> web request ->
// @Controller  // ! web layer listener (server), @Controller + @GetMapping
// @ResponseBody  // Return JSON
@RestController  // ! @RestController = @Controller + @ResponseBody
public class CalculatorController {

  @GetMapping("/sum/{x}/{y}")
  public Integer sum(@PathVariable Integer x, @PathVariable Integer y) {
    return x + y;
  }

  @GetMapping("/subtract/{x}/{y}")
  public Integer substract(@PathVariable Integer x, @PathVariable Integer y) {
    return x - y;
  }

  @GetMapping("/multiple/{x}/{y}")
  public Double multiply2(@PathVariable(value = "x") Double salary, 
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
      return -10_000_000.0;
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
  // ! handle String null

  // @GetMapping("/calculator/{operation}/{x}/{y}")
  // public Double calculate(@PathVariable String operation,
  //  @PathVariable String x, @PathVariable String y) {
  //    if (operation != null && ("sum".equals(operation) || "subtract".equals(operation)
  //      || "multiple".equals(operation) || "divide".equals(operation) )) {
  //    } else {

  //    }
  // }

  @GetMapping("/calculator/{operation}/{x}/{y}")
  public Double calculateSwitch(@PathVariable String operation,
    @PathVariable String x, @PathVariable String y) {
    if (operation == null)
      return -500_000_000.0;
    Convertor c = new Convertor();
    return switch (operation) {
          case "sum" -> Calculators.sum(c.convert(x), c.convert(y));
          case "subtract" -> Calculators.subtract(c.convert(x), c.convert(y));
          case "multiply" -> Calculators.multiply(c.convert(x), c.convert(y));
          case "divide" -> Calculators.divide(c.convert(x), c.convert(y));
          default -> -999_999_999.0;
    };
  }

  // private static Double sum2(Double x, Double y) {
  //   return BigDecimal.valueOf(x).add(BigDecimal.valueOf(y)).doubleValue();
  // }

  // private static Double subtract2(Double x, Double y) {
  //   return BigDecimal.valueOf(x).subtract(BigDecimal.valueOf(y)).doubleValue();
  // }

  // private static Double multiply2(Double x, Double y) {
  //   return BigDecimal.valueOf(x).multiply(BigDecimal.valueOf(y)).doubleValue();
  // }

  // private static Double divide2(Double x, Double y) {
  //   return BigDecimal.valueOf(x).divide(BigDecimal.valueOf(y)).doubleValue();
  // }

  // private static Double convert(String s) {
  //   try {
  //     return Double.valueOf(s);
  //   } catch (NumberFormatException e) {
  //     return -99_999_999.00;
  //   }
  // }
  

}
