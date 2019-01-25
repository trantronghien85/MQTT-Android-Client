var mqtt = require('mqtt');
var client  = mqtt.connect('mqtt://localhost:1883');
var count = 0;
client.on('connect', function () {
setInterval(function() {
client.publish('demo', 'message mqtt ' + count);
    console.log('Message Sent ' + count);
	count++;
}, 5000);
});