package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.exception;

import java.util.ArrayList;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.ApiUnavailableException;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.DatabaseConnectionFailException;
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

  @ExceptionHandler(value = ApiUnavailableException.class)
  public GeneralResponse<String> handleApiUnavailableException(ApiUnavailableException e) {
    return GeneralResponse.<String>builder()
      .config(SysCode.codeOf(e.getCode()))
      .build();
  }

  @ExceptionHandler(value = DatabaseConnectionFailException.class)
  public GeneralResponse<String> handleDatabaseConnectionFailException(DatabaseConnectionFailException e) {
    return GeneralResponse.<String>builder()
      .config(SysCode.codeOf(e.getCode()))
      .build();
  }

  // <Void> 不可 return List, 而 <Object> 可 return List.
  // @ExceptionHandler(value = RestClientException.class)
  // public GeneralResponse<Void> handleServiceNotFoundException(RestClientException e) {
  //   return GeneralResponse.<Void>builder()
  //     .config(SysCode.SERVICE_NOT_FOUND)
  //     .build();
  // }

  @ExceptionHandler(value = RestClientException.class)
  public GeneralResponse<Object> handleServiceNotFoundException(RestClientException e) {
    return GeneralResponse.<Object>builder()
      .config(SysCode.SERVICE_NOT_FOUND)
      .data(new ArrayList<>())
      .build();
  }

  @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
  public GeneralResponse<Object> handleInvalidSqlException(InvalidDataAccessResourceUsageException e) {
    return GeneralResponse.<Object>builder()
      .config(SysCode.SQL_INVALID)
      .data(new ArrayList<>())
      .build();
  }
}


