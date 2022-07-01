package com.ryo616.miniRend.core.window;

public class AspectRatio {

    private int width, height;

    public AspectRatio(int width, int height){
        this.width = width;
        this.height = height;

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public double getAsRatio(){
        return (this.width/this.height);
    }
}
