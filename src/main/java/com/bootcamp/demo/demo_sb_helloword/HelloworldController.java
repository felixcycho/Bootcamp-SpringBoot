package com.bootcamp.demo.demo_sb_helloword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class HelloworldController {
  @GetMapping(value = "/helloworld")
  public String greeting() {
      return "hello world! welcome to bootcamp";
  }
  
}
