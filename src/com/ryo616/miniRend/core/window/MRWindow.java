package com.ryo616.miniRend.core.window;

public class MRWindow{
    private static MRWindow instance = null;

    private String title;
    private int width, height;

    private MRWindow(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public static void createWindow(String title, int width, int height){
        if (instance == null)
            instance = new MRWindow(title, width, height);
        else throw new RuntimeException("You have already constructed this window! Use getInstance() instead.");
    }

    public static MRWindow getInstance(){
        return instance;
    }
}
