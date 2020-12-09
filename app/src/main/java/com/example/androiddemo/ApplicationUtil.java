package com.example.androiddemo;

public class ApplicationUtil {
    private static volatile ApplicationUtil instance;
    private MyApplication application;

    public static ApplicationUtil getInstance() {
        if (instance == null) {
            synchronized (ApplicationUtil.class) {
                if (instance == null) {
                    instance = new ApplicationUtil();
                }
            }
        }
        return instance;
    }


    public MyApplication getApplication() {
        return application;
    }

    public void setApplication(MyApplication application) {
        this.application = application;
    }
}
