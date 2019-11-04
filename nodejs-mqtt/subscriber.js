
var mqtt = require('mqtt');
var url = require('url');
var TOPIC = 'chanel';
var mqtt_url = url.parse(process.env.CLOUDMQTT_URL || 'mqtt://192.168.0.121:1883');
var auth = (mqtt_url.auth || ':').split(':');

// Create a client connection
var client  = mqtt.connect(mqtt_url.href);
console.log("end point: " + mqtt_url.href);

client.on('connect', function () {
    client.subscribe(TOPIC)
    console.log("subscribe connect");
})

client.on('message', function (topic, message) {
    console.log("subscribe message on topic: " + topic);
    var context = message.toString();
    console.log(context)
})