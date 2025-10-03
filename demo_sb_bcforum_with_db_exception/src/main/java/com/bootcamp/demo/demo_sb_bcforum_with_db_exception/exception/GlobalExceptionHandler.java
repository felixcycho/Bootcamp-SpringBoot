package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.GeneralResponse;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.NotFoundException;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.SysCode;

// ! 全局攔截
@RestControllerAdvice
public class GlobalExceptionHandler {
  // ! 專業攔截 NumberFormatException
  // @ExceptionHandler(value = NumberFormatException.class)
  // public String handleNumberFormatException(NumberFormatException e) {
  //   return "I got issue, please help. Reason = " + e.getMessage();
  // }

  @ExceptionHandler(value = NumberFormatException.class)
  public GeneralResponse<ErrorMessage> handleNumberFormatException(NumberFormatException e) {
    ErrorMessage errorMessage 
     = ErrorMessage.builder()
      .code(999)
      .message("I got issue, please help. Reason = " + e.getMessage())
      .build();
    return GeneralResponse.<ErrorMessage>builder()
      // .code(999)
      // .message("System error.")
      .fail()
      .data(errorMessage)
      .build();
  }

  @ExceptionHandler(value = NotFoundException.class)
  public GeneralResponse<String> handleNotFoundException(NotFoundException e) {
    return GeneralResponse.<String>builder()
      .config(SysCode.codeOf(e.getCode())) // 99999, "Fail."
      .data(e.getMessage())
      .build();
  }
}
