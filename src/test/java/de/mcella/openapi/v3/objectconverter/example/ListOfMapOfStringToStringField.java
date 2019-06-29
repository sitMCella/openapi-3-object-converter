package de.mcella.openapi.v3.objectconverter.example;

import java.util.List;
import java.util.Map;

public class ListOfMapOfStringToStringField {
  public final List<Map<String, String>> fields;

  public ListOfMapOfStringToStringField(List<Map<String, String>> fields) {
    this.fields = fields;
  }
}
