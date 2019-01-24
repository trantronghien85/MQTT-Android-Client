package com.it.hientran.mqtt.lisetener;

public interface MqttClientConnectListener {
    void onConnectSuccess();

    void onConnectFailed(String message);
}
