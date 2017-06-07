package com.gudgie.rxsensors.adapter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.gudgie.rxsensors.model.Rotation;

import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by luke on 07/06/17.
 */

public class SensorAdapter {

    private PublishSubject<Rotation> rotationSubject = PublishSubject.create();

    private static SensorAdapter instance;

    private SensorManager sensorManager;

    private Sensor rotationSensor;

    private Rotation rotationData;

    public static SensorAdapter get(Context context) {
        if (instance == null) {
            instance = new SensorAdapter(context);
        }
        return instance;
    }

    private SensorAdapter(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(rotationEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void subscribeToRotation(Subscriber<? super Rotation> subscriber) {
        if (rotationSensor == null) {
            rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        }
        rotationSubject.subscribe(subscriber);
    }

    private SensorEventListener rotationEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(rotationData == null) {
                rotationData = new Rotation();
            }
            rotationData.setValues(event.values);
            rotationSubject.onNext(rotationData);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // DO NOTHING
        }
    };
}
