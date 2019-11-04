var mqtt = require('mqtt')
var url = require('url');
var mosca = require('mosca');
// Parse
// var mqtt_url = url.parse(process.env.CLOUDMQTT_URL || 'mqtt://localhost:1883');


// var server  = mqtt.connect(mqtt_url.href);

var PORT = process.env.PORT || 1883;
var mqtt_url = url.parse(`ws://mqtt-example-tth.herokuapp.com:${PORT}`);
console.log("end point: " + mqtt_url.href);
console.log("PORT: " + PORT);

var pubsubsettings = {
  //using ascoltatore
  // type: 'mongo',		
  url: mqtt_url.href,
  pubsubCollection: 'ascoltatori',
  // mongo: {}
};

var moscaSettings = {
  // port: 80,
  backend: pubsubsettings
};

var server = mosca.Server(moscaSettings);

server.on('ready', function () {
  console.log('Mosca server is up and running');
  // var message = {
  //   topic: 'chanel',
  //   payload: 'abcde', // or a Buffer
  //   qos: 0, // 0, 1, or 2
  //   retain: false // or true
  // };
  
  // server.publish(message, function() {
  //   console.log('done!');
  // });
});

server.on('clientConnected', function(client) {
  // console.log('client connected', client);
  var id = !client ? "" : client.id;
	console.log('client connected ' + id);		
});

//when a client has sent back a puback for a published message 
server.on('delivered', function(packet, client){
  console.log('delivered');
});

// Receiving data from clients 
server.on('published', function(packet, client) {
  var id = !client ? "" : client.id;
  console.log('Published ' + id);
  console.log(`Published topic: ${packet.topic}   payload:  ${packet.payload.toString()}`);
});
 
server.on('subscribed', function(packet ,client) {
  var id = !client ? "" : client.id;
  console.log('subscribed: ' + id);
});

// when a client is unsubscribed to a topic
server.on('unsubscribed', function(client) {
  console.log('unsubscribed ' + client);
});
 
server.on('clientDisconnecting ', function(client){
  var id = !client ? "" : client.id;
  console.log('clientDisconnecting ' + id);
});

server.on('clientDisconnected', function(client){
  var id = !client ? "" : client.id;
  console.log('clientDisconnected ' + id);
});

