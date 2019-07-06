package de.mcella.openapi.v3.objectconverter.example;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public class ExampleComplexObjectFromExternal {
  public final List<Map<String, ExampleFromExternal>> field1;
  public final Map<String, Map<String, List<Map<String, ExampleListOfClassFromExternal>>>> field2;
  private final List<LocalDate> field3;
  private final Map<String, LocalDate> field4;
  public final Map<String, ExampleLocalDateFieldFromExternal> field5;
  private final List<OffsetDateTime> field6;
  private final Map<String, OffsetDateTime> field7;
  public final Map<String, ExampleOffsetDateTimeFieldFromExternal> field8;

  public ExampleComplexObjectFromExternal(
      List<Map<String, ExampleFromExternal>> field1,
      Map<String, Map<String, List<Map<String, ExampleListOfClassFromExternal>>>> field2,
      List<LocalDate> field3,
      Map<String, LocalDate> field4,
      Map<String, ExampleLocalDateFieldFromExternal> field5,
      List<OffsetDateTime> field6,
      Map<String, OffsetDateTime> field7,
      Map<String, ExampleOffsetDateTimeFieldFromExternal> field8) {
    this.field1 = field1;
    this.field2 = field2;
    this.field3 = field3;
    this.field4 = field4;
    this.field5 = field5;
    this.field6 = field6;
    this.field7 = field7;
    this.field8 = field8;
  }
}
