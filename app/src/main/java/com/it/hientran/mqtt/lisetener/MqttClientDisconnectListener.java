package com.it.hientran.mqtt.lisetener;

public interface MqttClientDisconnectListener {
    void onDisconnectSuccess();

    void onDisconnectFailed(String message);
}
