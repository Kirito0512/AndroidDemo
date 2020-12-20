package com.example.androiddemo.gson;

import java.io.Serializable;

public class TestBaseModel implements Serializable {
    private static final long serialVersionUID = 430789164272065602L;
    private String name;
    private String sex;

    public TestBaseModel(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
