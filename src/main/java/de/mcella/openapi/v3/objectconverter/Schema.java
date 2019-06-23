package de.mcella.openapi.v3.objectconverter;

import java.util.Map;

public class Schema {

  public Map<String, Object> schema;

  public Schema(Map<String, Object> schemaDefinition) {
    schema = schemaDefinition;
  }
}
