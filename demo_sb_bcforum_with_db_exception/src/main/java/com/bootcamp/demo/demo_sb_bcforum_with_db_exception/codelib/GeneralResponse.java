package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib;

import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.exception.ErrorMessage;

public class GeneralResponse<T> {
  private Integer code;
  private String message;
  private T data;
  
  public Integer getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }

  public T getData() {
    return this.data;
  }

  // Static method to create a new Builder instance
  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  // Constructor
  public GeneralResponse(Builder<T> builder) {
    this.code = builder.code;
    this.message = builder.message;
    this.data = builder.data;
  }

  // Builders
  public static class Builder<T> {
    private Integer code;
    private String message;
    private T data;

    public Builder<T> code(Integer code) {
      this.code = code;
      return this;
    }

    public Builder<T> message(String message) {
      this.message = message;
      return this;
    }
    
    public Builder<T> data(T data) {
      this.data = data;
      return this;
    }

    // Build method to create a GeneralResponse instance
    public GeneralResponse<T> build() {
      return new GeneralResponse<>(this);
    }
  }

  public static void main(String[] args) {
    GeneralResponse<ErrorMessage> response = GeneralResponse.<ErrorMessage>builder()
      .code(999)
      .message("Testing")
      .data(new ErrorMessage(999, "ABCDE"))
      .build();
    
    System.out.println(response.getCode());
    System.out.println(response.getMessage());
    System.out.println(response.getData());
  }
}
