# Deloy MQTT in heroku 
 + log heroku `heroku login`
 + create app `heroku create --app {app name}`  log return `{app name} ` after create success
 + add repository remote heroku to local git ` heroku git:remote -a {app name}` ex ` heroku git:remote -a mqtt-example-tth`
 + add to git `git add .`
 + comit `git commit -m "message"`
 + push and deloy to heroku `git push heroku master`
 
# Test with client node 
+ go to dir  `cd nodejs-mqtt`
+ run client subscriber and publisher `node publisher.js` after `node subscriber.js` 

# Model
![mqtt protocol](https://github.com/trantronghien/MQTT-Android-Client/blob/master/mqtt-protocol.png)
