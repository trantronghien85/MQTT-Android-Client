var mqtt = require('mqtt');
var url = require('url');
var TOPIC = 'chanel';
var mqtt_url = url.parse(process.env.CLOUDMQTT_URL || 'ws://mqtt-example-tth.herokuapp.com:38369');
var auth = (mqtt_url.auth || ':').split(':');

// Create a client connection
console.log("end point: " + mqtt_url.href);
var client  = mqtt.connect(mqtt_url.href);

var count = 0;
client.on('connect', function () {
    console.log("publisher connect");
    setInterval(function () {
        client.publish(TOPIC, 'message mqtt ' + count);
        console.log('Message Sent ' + count);
        count++;
    }, 2000);
});