package io.camunda.connector;

import org.json.JSONObject;
import javax.validation.constraints.NotEmpty;


public class NATSConnectorRequest {

  @NotEmpty
  private String url;

  private String topic;

  private String message;

  public static final String URL = "url";

  public static final String TOPIC = "topic";

  public static final String MESSAGE = "message";

  public NATSConnectorRequest() {
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }






}
