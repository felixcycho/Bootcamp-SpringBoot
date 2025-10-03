package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
  private Integer code;
  private String message;
}
