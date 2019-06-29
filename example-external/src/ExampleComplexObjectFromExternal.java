package de.mcella.openapi.v3.objectconverter.example;

import java.util.List;
import java.util.Map;

public class ExampleComplexObjectFromExternal {
  public final List<Map<String, ExampleFromExternal>> field1;
  public final Map<String, Map<String, List<Map<String, ExampleListOfClassFromExternal>>>> field2;

  public ExampleComplexObjectFromExternal(
      List<Map<String, ExampleFromExternal>> field1,
      Map<String, Map<String, List<Map<String, ExampleListOfClassFromExternal>>>> field2) {
    this.field1 = field1;
    this.field2 = field2;
  }
}
