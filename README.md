## Overview
This adapter will send EVENT notification to hipchat room.
[Jennifersoft github](https://github.com/jennifersoft)

## Getting started
You must modify the configuration file of the view server. (conf/server_view.conf)
```
adapter_class_path = /usr/local/jennifer/jennifer5/server.view/ext/email.adapter/jennifer-view-adapter-hipchat.jar
adapter_config_path = /usr/local/jennifer/jennifer5/server.view/ext/team.adapter/adapter.properties
adapter_event_class_name = adapter.jennifer.team.TeamAdapter
```

## Configuration file
The configuration file looks like the following
```
# Set Microsoft Team 'Incoming Webhook' URL for normal message
url https://outlook.office.com/webhook/aeae5034-6442-4cb4-ba35-97ed774ea633@53a8b0d9-d900-48cc-9d7e-5935dc8d5b17/IncomingWebhook/23fcd89a59bb4541ac028ac4211ed160/2a8c4607-13e0-407c-bfea-32c566b7c64b

# Set Microsoft Team 'Incoming Webhook' URL for Fatal message
url_fatal https://outlook.office.com/webhook/aeae5034-6442-4cb4-ba35-97ed774ea633@53a8b0d9-d900-48cc-9d7e-5935dc8d5b17/IncomingWebhook/3f12522575af4eba9c23f236173aace1/2a8c4607-13e0-407c-bfea-32c566b7c64b

# Set proxy setting
# Example : "on" or "off"
# Example : on -> user proxy, off -> direct access
proxy_on=off
proxy_host=proxy-host
proxy_port=9999

```

* Make sure to replace the Microsoft Team Incoming Webhook property with the correct value

## version history
2018-10-24 : v1.0.0 default functions. 

## For develop
Add class path
```
{{ VIEW_SERVER_HOME }}/lib
```
For avoid "@Override" error in TeamAdapter
```
Project language level: 6 - @Override in interfaces
```
