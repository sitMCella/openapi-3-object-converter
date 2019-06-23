package de.mcella.openapi.v3.objectconverter;

import java.util.List;
import java.util.Map;

public class ListOfMapOfStringToIntegerWrapperClassField {
  public final List<Map<String, IntegerWrapperField>> fields;

  public ListOfMapOfStringToIntegerWrapperClassField(
      List<Map<String, IntegerWrapperField>> fields) {
    this.fields = fields;
  }
}
