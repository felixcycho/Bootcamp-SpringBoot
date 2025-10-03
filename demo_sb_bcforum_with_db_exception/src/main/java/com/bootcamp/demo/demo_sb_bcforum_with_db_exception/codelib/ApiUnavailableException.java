package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib;

public class ApiUnavailableException extends BusinessException {
  public ApiUnavailableException(SysCode sysCode) {
    super(sysCode);
  }
}
