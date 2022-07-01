package com.ryo616.miniRend.core.window;

import com.ryo616.miniRend.core.general.MRLoopable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class MRWindow implements MRLoopable {
    private static MRWindow instance = null;

    private String title;
    private int width, height;
    private JFrame frame;
    private Canvas display;
    private BufferedImage displayBuffer;

    private int[] pixelBuffer;
    private float[] depthBuffer;

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

    public void init(){
        frame = new JFrame();

    }

    private void regenerateBuffers(){
        displayBuffer = frame.getGraphicsConfiguration().createCompatibleImage(width, height);
        pixelBuffer = ((DataBufferInt) displayBuffer.getRaster().getDataBuffer()).getData();
        depthBuffer = new float[pixelBuffer.length];
    }

    public static MRWindow getInstance(){
        return instance;
    }

    @Override
    public void loop() {

    }
}
