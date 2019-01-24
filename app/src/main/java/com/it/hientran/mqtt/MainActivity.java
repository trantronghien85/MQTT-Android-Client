package com.it.hientran.mqtt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.it.hientran.mqtt.lisetener.MqttClientConnectListener;

public class MainActivity extends AppCompatActivity implements MqttClientConnectListener{

    private MqttClient mqttClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = findViewById(R.id.btnStart);
        mqttClient = new MqttClient(getApplicationContext());
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttClient.connect();
            }
        });
    }

    @Override
    public void onConnectSuccess() {

    }

    @Override
    public void onConnectFailed(String message) {

    }
}
