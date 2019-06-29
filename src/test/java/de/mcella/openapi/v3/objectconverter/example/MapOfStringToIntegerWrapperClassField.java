package de.mcella.openapi.v3.objectconverter.example;

import java.util.Map;

public class MapOfStringToIntegerWrapperClassField {
  public final Map<String, IntegerWrapperField> fields;

  public MapOfStringToIntegerWrapperClassField(Map<String, IntegerWrapperField> fields) {
    this.fields = fields;
  }
}
