package com.ryo616.miniRend.core.maths;

import com.ryo616.miniRend.core.window.AspectRatio;

import java.io.IOException;

public class Matrix3fBuilder {
    public static Matrix3f buildTranslation(Vector2f translation){
        return new Matrix3f(
                new float[]{1, 0, translation.x,
                            0, 1, translation.y,
                            0, 0,             1});
    }

    public static Matrix3f buildDilation(float xFactor, float yFactor){
        return new Matrix3f(
                new float[]{xFactor,       0, 0,
                                  0, yFactor, 0,
                                  0,       0, 1});
    }

    public static Matrix3f buildDilation(float factor){
        return new Matrix3f(
                new float[]{factor,      0, 0,
                                 0, factor, 0,
                                 0,      0, 1});
    }

    public static Matrix3f buildProjection(double fov, AspectRatio aspectRatio) throws IOException {
        double ar = aspectRatio.getAsRatio();
        double tanCheck = Math.tan(fov/2);
        if (tanCheck == 0) throw new IOException("Matrix3fBuilder Error: fov value is invalid!");
        double f = 1/tanCheck;
        return new Matrix3f(
                new float[]{(float) (ar * f),         0, 0,
                                           0, (float) f, 0,
                                           0,         0, 1});
    }

    public static Matrix3f buildCamera(double camAngle, Vector2f translation){
        Vector2f right = new Vector2f((float) Math.cos(camAngle * MathsEx.deg2rad), (float) Math.sin(camAngle * MathsEx.deg2rad));
        Vector2f up = new Vector2f(-right.y, right.x);
        float ta = -Vector2f.dot(right, translation);
        float tb = -Vector2f.dot(up, translation);
        return new Matrix3f(
                new float[]{right.x, right.y, ta,
                               up.x,    up.y, tb,
                                  0,       0, 1});
    }

    public static Matrix3f buildPointAt(Vector2f target, Vector2f translation){
        Vector2f t = target.normalized();
        Vector2f tNormal = new Vector2f(-target.y, target.x);
        return new Matrix3f(
                new float[]{t.x, tNormal.x, translation.x,
                            t.y, tNormal.y, translation.y,
                              0,         0,             1});
    }
}
