package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib;

public class NotFoundException extends BusinessException {
  public NotFoundException(SysCode sysCode) {
    super(sysCode);
  }
}
