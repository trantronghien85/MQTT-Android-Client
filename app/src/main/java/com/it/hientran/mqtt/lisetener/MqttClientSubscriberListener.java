package com.it.hientran.mqtt.lisetener;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttClientSubscriberListener {
    void onSubscribe(String topic, MqttMessage message);
}
