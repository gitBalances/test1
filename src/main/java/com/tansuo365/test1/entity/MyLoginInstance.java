package com.tansuo365.test1.entity;

public interface MyLoginInstance {

    String getInstanceName();

    String getInstancePassword();

    String getInstanceSalt();

    void setInstanceName(String username);

    void setInstancePassword(String password);

    void setInstanceSalt(String salt);
}
