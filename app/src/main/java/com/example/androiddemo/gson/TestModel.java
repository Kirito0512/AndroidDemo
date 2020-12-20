package com.example.androiddemo.gson;

public class TestModel extends TestBaseModel {
    private static final long serialVersionUID = -4851458767976999725L;
    private String age;


    public TestModel(String name, String sex, String age) {
        super(name, sex);
        this.age = age;
    }
}
