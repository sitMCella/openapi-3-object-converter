package de.mcella.openapi.v3.objectconverter.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ExampleComplexObjectFromExternal {
  public final List<Map<String, ExampleFromExternal>> field1;
  public final Map<String, Map<String, List<Map<String, ExampleListOfClassFromExternal>>>> field2;
  private final List<LocalDate> field3;
  private final Map<String, LocalDate> field4;
  public final Map<String, ExampleLocalDateFieldFromExternal> field5;

  public ExampleComplexObjectFromExternal(
      List<Map<String, ExampleFromExternal>> field1,
      Map<String, Map<String, List<Map<String, ExampleListOfClassFromExternal>>>> field2,
      List<LocalDate> field3,
      Map<String, LocalDate> field4,
      Map<String, ExampleLocalDateFieldFromExternal> field5) {
    this.field1 = field1;
    this.field2 = field2;
    this.field3 = field3;
    this.field4 = field4;
    this.field5 = field5;
  }
}
