package com.gudgie.rxsensors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gudgie.rxsensors.adapter.SensorAdapter;
import com.gudgie.rxsensors.model.Rotation;

import rx.Subscriber;

/**
 * Created by luke on 07/06/17.
 */

public class SensorActivity extends AppCompatActivity {

    private TextView rotationSensorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        rotationSensorView = (TextView) findViewById(R.id.rotation_sensor_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorAdapter.get(this).subscribeToRotation(new Subscriber<Rotation>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Rotation rotationData) {
                rotationSensorView.setText(
                        getString(R.string.rotation_data_string,
                                rotationData.getX(),
                                rotationData.getY(),
                                rotationData.getZ(),
                                rotationData.getScalar()));
            }
        });
    }
}
