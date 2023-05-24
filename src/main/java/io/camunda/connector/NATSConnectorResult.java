package io.camunda.connector;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NATSConnectorResult {

  public static final String OUTPUT_VARIABLE = "output";

  // TODO: define connector result properties, which are returned to the process engine
  private HashMap<String, Object> output = new HashMap();

  public HashMap<String, Object> getoutput() {
    return output;
  }

  public void setResult(String output) {
    Gson gson = new Gson();
    Map map = gson.fromJson(output, Map.class);
    this.output.put("body", map);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final NATSConnectorResult that = (NATSConnectorResult) o;
    return Objects.equals(output, that.output);
  }

  @Override
  public int hashCode() {
    return Objects.hash(output);
  }

  @Override
  public String toString() {
    return "MyConnectorResult [output=" + output + "]";
  }

}
