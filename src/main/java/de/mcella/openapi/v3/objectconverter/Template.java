package de.mcella.openapi.v3.objectconverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {

  public final String openapi = "3.0.0";
  public final Map<String, String> info;
  public final List<Server> servers;
  public final Map<String, Map<String, Request>> paths;

  public Template() {
    info =
        Map.of(
            "title",
            "API example",
            "description",
            "Generated API example from template class",
            "version",
            "1.0.0");
    servers = List.of(new Server());
    paths = Map.of("/sample", Map.of("post", new Request()));
  }

  public void addRequestBodyContent(Schema schema) {
    paths.get("/sample").get("post").requestBody.content.put("application/json", schema);
  }

  private class Server {
    @SuppressWarnings("unused")
    public final String url = "http://localhost:8080";
  }

  private class Request {
    @SuppressWarnings("unused")
    public final String summary = "Sample post request";

    @SuppressWarnings("unused")
    public final String description = "Generated sample post request from template class";

    public final RequestBody requestBody = new RequestBody();

    @SuppressWarnings("unused")
    public final Map<String, Response> responses = Map.of("200", new Response());
  }

  private class RequestBody {
    @SuppressWarnings("unused")
    public final String description = "Generated content body";

    public final Map<String, Schema> content = new HashMap<>();
  }

  private class Response {
    @SuppressWarnings("unused")
    public final String description = "Generated sample post response from template class";
  }
}
