package de.mcella.openapi.v3.objectconverter.example;

import java.util.Map;

public class MapOfStringToIntegerField {
  public final Map<String, Integer> fields;

  public MapOfStringToIntegerField(Map<String, Integer> fields) {
    this.fields = fields;
  }
}
