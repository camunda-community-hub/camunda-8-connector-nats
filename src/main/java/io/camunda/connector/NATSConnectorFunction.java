package io.camunda.connector;


import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;

import io.nats.client.Connection;
import io.nats.client.Nats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@OutboundConnector(
    name = "NATSConnector",
    inputVariables = {"url", "topic", "message"},
    type = NATSConnectorFunction.TYPE_CONNECTOR
)

public class NATSConnectorFunction implements OutboundConnectorFunction {

  public static final String TYPE_CONNECTOR = "io.camunda:nats";
  private static final Logger LOGGER = LoggerFactory.getLogger(NATSConnectorFunction.class);


  //@Override
  public NATSConnectorResult execute(OutboundConnectorContext context) throws java.io.IOException {
    var connectorRequest = context.getVariablesAsType(NATSConnectorRequest.class);
    context.validate(connectorRequest);
    context.replaceSecrets(connectorRequest);

    return executeConnector(connectorRequest);
  }

  private NATSConnectorResult executeConnector(final NATSConnectorRequest connectorRequest) {

    String output = ""; // Output from NATS

    try {
      Connection natsConnection = Nats.connect(connectorRequest.getUrl());

      natsConnection.publish(connectorRequest.getTopic(), connectorRequest.getMessage().getBytes());

      // Get output
      output = "{\"result\": {\"output\": \"Called the NATS connector\"}}";

      natsConnection.close();
      
    } catch(Exception e) {
      LOGGER.error("Error in NATS connector "+e);
    }

    // Send output back to Camunda
    var result = new NATSConnectorResult();
    result.setResult(output);
    return result;
  }

}
