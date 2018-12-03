## Overview
This adapter will send EVENT notification to mirosoft team.
[Jennifersoft github](https://github.com/jennifersoft

## Getting started

Execute the following in the Jennifer management screen.

 1. Extension & Notice > Adapter and Plugin.
 2. Select the Adapter tab.
 3. Click the Add button.
 4. Select the 'EVENT' type.
 5. Enter the 'team' ID.
 6. Enter the path to the 'dist/adapter-team-1.0.0.jar' file or upload it yourself.
 ![screen shot 2018-11-29 at 14 01 43](https://user-images.githubusercontent.com/2956728/49200280-702c2d00-f3df-11e8-9f2e-6b06aaa644be.png)
 7. set token and url to options
 ![screen shot 2018-11-29 at 14 09 17](https://user-images.githubusercontent.com/2956728/49200485-65be6300-f3e0-11e8-8559-a36c167c95d4.png)
 8. Restart jennifer view instance.
 
## How to use options

Plugin options are shown in the table below.

| Key           | Value | Remark |
| ------------- |:-------------:|:-------------:|
| url |  https://outlook.office.com/webhook/aeae5....... | notification 1 |
| url_fatal |  https://outlook.office.com/webhook/aeae5....... | notification 2 |
| proxy_on | off | "on" or "off" |
| proxy_host | my.proxy.com | proxy server host |
| proxy_prot | 3120 | proxy port number |

## Supported version
 
Different versions of the server support different plug-in versions.
 
| Plugin version | Jennifer server version | Java version |
| ------------- |-------------|-------------|
| 1.0.0       | Greater than or equal to version 5.4.0.0 | 1.8