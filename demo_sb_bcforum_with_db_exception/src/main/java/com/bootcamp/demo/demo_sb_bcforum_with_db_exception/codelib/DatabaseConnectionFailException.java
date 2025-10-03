package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib;

public class DatabaseConnectionFailException extends BusinessException {
  public DatabaseConnectionFailException(SysCode sysCode) {
    super(sysCode);
  }
}
