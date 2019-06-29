package de.mcella.openapi.v3.objectconverter.example;

import java.util.List;
import java.util.Map;

public class MapOfStringToListOfIntegerWrapperClassField {
  public final Map<String, List<IntegerWrapperField>> fields;

  public MapOfStringToListOfIntegerWrapperClassField(
      Map<String, List<IntegerWrapperField>> fields) {
    this.fields = fields;
  }
}
