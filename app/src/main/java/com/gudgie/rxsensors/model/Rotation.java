package com.gudgie.rxsensors.model;

/**
 * Created by luke on 07/06/17.
 */

public class Rotation extends SensorData {

    private float x;

    private float y;

    private float z;

    private float scalar;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getScalar() {
        return scalar;
    }

    public void setScalar(float scalar) {
        this.scalar = scalar;
    }

    public void setValues(float[] values) {
        this.x = values[0];
        this.y = values[1];
        this.z = values[2];
        this.scalar = values[3];
    }
}
