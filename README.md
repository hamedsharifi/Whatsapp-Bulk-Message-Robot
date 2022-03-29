# WhatsApp bulk message sender robot using Spring Boot
With this robot you can send bulk message as desired. You have to setup the program database table first for daily task. Each record in that table means a day.
for create every day program use another app in:
```
https://github.com/hamedsharifi/WhatsappRobotProgramGenerator
```
Application write sent and received messages in Postgre database.
Be careful of count of messages you will send daily. WhatsApp may block you if the detect a spammy activity. 
