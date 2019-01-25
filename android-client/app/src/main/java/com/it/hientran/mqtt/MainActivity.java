package com.it.hientran.mqtt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.it.hientran.mqtt.lisetener.MqttClientConnectListener;
import com.it.hientran.mqtt.lisetener.MqttClientSubscriberListener;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements MqttClientConnectListener , MqttClientSubscriberListener {

    private MqttClient mqttClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = findViewById(R.id.btnStart);
        Button btnPublish = findViewById(R.id.btnPublish);
        mqttClient = new MqttClient(getApplicationContext());
        mqttClient.setMqttClientConnectListener(this);
        mqttClient.setMqttClientSubscriberListener(this);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttClient.connect();
            }
        });
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mqttClient.Publish("i'am android client ");
            }
        });
    }

    @Override
    public void onConnectSuccess() {

    }

    @Override
    public void onConnectFailed(String message) {

    }

    @Override
    public void onSubscribe(String topic, MqttMessage message) {
        String messConvert = "";
        try {
            messConvert = new String(message.getPayload() , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, messConvert, Toast.LENGTH_SHORT).show();
    }
}
