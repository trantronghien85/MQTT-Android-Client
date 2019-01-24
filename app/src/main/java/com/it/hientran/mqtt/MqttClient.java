package com.it.hientran.mqtt;

import android.content.Context;
import android.util.Log;

import com.it.hientran.mqtt.lisetener.MqttClientConnectListener;
import com.it.hientran.mqtt.lisetener.MqttClientDisconnectListener;
import com.it.hientran.mqtt.lisetener.MqttClientSubscriberListener;
import com.it.hientran.mqtt.lisetener.MqttClientUnsubscribeListener;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttClient {
    private MqttAndroidClient client;
    private final String TAG = "debug-mqtt";
    private MqttClientConnectListener mMqttClientConnectListener;
    private MqttClientDisconnectListener mMqttClientDisconnectListener;
    private MqttClientSubscriberListener mMqttClientSubscriberListener;
    private MqttClientUnsubscribeListener mMqttClientUnsubscribeListener;

    public MqttClient(Context context) {
        if (client == null) {
            String clientId = org.eclipse.paho.client.mqttv3.MqttClient.generateClientId();
            client = new MqttAndroidClient(context, Constants.URL, clientId);
        }
    }

    public void Publish() {
        MqttMessage message = new MqttMessage();
        try {
            client.publish(Constants.TOPIC, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe() {
        int qos = 1;
        try {
            IMqttMessageListener iMqttMessageListener = new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    if (mMqttClientSubscriberListener != null){
                        mMqttClientSubscriberListener.onSubscribe(topic , message);
                    }
                }
            };

            client.subscribe(Constants.TOPIC, qos, iMqttMessageListener);
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(TAG, "onSubscribe: ", e);
        }
    }

    public void onUnsubscribe() {
        IMqttToken unsubToken = null;
        try {
            unsubToken = client.unsubscribe(Constants.TOPIC);
            unsubToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    if (mMqttClientUnsubscribeListener != null){
                        mMqttClientUnsubscribeListener.onUnsubscribeSuccess();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    if (mMqttClientUnsubscribeListener != null){
                        mMqttClientUnsubscribeListener.onUnsubscribeFailed(exception.getMessage());
                    }
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            client.disconnect().setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    if (mMqttClientDisconnectListener != null) {
                        mMqttClientDisconnectListener.onDisconnectSuccess();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    if (mMqttClientDisconnectListener != null) {
                        mMqttClientDisconnectListener.onDisconnectFailed(exception.getMessage());
                    }
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            client.connect().setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken mqttToken) {
                    if (mMqttClientConnectListener != null) {
                        mMqttClientConnectListener.onConnectSuccess();
                    }
                    Log.i(TAG, "onSuccess: connected");
                    subscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    if (mMqttClientConnectListener != null) {
                        mMqttClientConnectListener.onConnectFailed(exception.getMessage());
                    }
                    Log.i(TAG, "onFailure: connect fail " + exception.getMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void setMqttClientConnectListener(MqttClientConnectListener mMqttClientConnectListener) {
        this.mMqttClientConnectListener = mMqttClientConnectListener;
    }

    public void setMqttClientDisconnectListener(MqttClientDisconnectListener mMqttClientDisconnectListener) {
        this.mMqttClientDisconnectListener = mMqttClientDisconnectListener;
    }

    public void setMqttClientSubscriberListener(MqttClientSubscriberListener mMqttClientSubscriberListener) {
        this.mMqttClientSubscriberListener = mMqttClientSubscriberListener;
    }

    public void setMqttClientUnsubscribeListener(MqttClientUnsubscribeListener mMqttClientUnsubscribeListener) {
        this.mMqttClientUnsubscribeListener = mMqttClientUnsubscribeListener;
    }
}
