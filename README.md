[![Community Extension](https://img.shields.io/badge/Community%20Extension-An%20open%20source%20community%20maintained%20project-FF4700)](https://github.com/camunda-community-hub/community)
[![](https://img.shields.io/badge/Lifecycle-Proof%20of%20Concept-blueviolet)](https://github.com/Camunda-Community-Hub/community/blob/main/extension-lifecycle.md#proof-of-concept-)
![Compatible with: Camunda Platform 8](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%208-0072Ce)

# NATS Outbound Connector

!!! Work in progress !!!

A starting point to build a NATS Connector to send messages to topics on NATS Servers. You'll need to provide the URL of the NATS Server, the topic you want to publish to, and the message you want to publish 
in the connector properties:

[More information on NATS can be found here](https://nats.io)

![](./images/setup.png)

For intermediate catch events you need to set a correlation key in the process using a variable and send the correlation key in the payload as part of the incoming message

## Build

You can package the Connector by running the following command:

```bash
mvn clean package
```

This will create the following artifacts:

- A thin JAR without dependencies.
- An uber JAR containing all dependencies, potentially shaded to avoid classpath conflicts. This will not include the SDK artifacts since those are in scope `provided` and will be brought along by the respective Connector Runtime executing the Connector.

### Shading dependencies

You can use the `maven-shade-plugin` defined in the [Maven configuration](./pom.xml) to relocate common dependencies
that are used in other Connectors and the [Connector Runtime](https://github.com/camunda-community-hub/spring-zeebe/tree/master/connector-runtime#building-connector-runtime-bundles).
This helps to avoid classpath conflicts when the Connector is executed.

Use the `relocations` configuration in the Maven Shade plugin to define the dependencies that should be shaded.
The [Maven Shade documentation](https://maven.apache.org/plugins/maven-shade-plugin/examples/class-relocation.html)
provides more details on relocations.

## API

### Connector Properties

This Connector can be configured with the following properties:


| Name                                                     | Description                                    | Example                               |
|----------------------------------------------------------|------------------------------------------------|---------------------------------------|
| NATS URL                                                 | URL of NATS Server                             | `localhost:4222`                      |
| Topic                                                    | Topic on NATS Server to monitor                | `sampleTopic`                         |
| Polling interval                                         | Long polling interval in seconds               | `60`                                  |

### Output

This Connector produces the following output:

```json
{
  "event": {
    "url": "localhost:4222",
    "topic": "sampleTopic",
    "message": "some message"
  }
}
```
# TODO / Next steps

- Add stuff like authentication and outputs and additional functionality


