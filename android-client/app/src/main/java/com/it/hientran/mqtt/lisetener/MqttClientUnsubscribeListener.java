package com.it.hientran.mqtt.lisetener;

public interface MqttClientUnsubscribeListener {
    void onUnsubscribeSuccess();

    void onUnsubscribeFailed(String message);
}
