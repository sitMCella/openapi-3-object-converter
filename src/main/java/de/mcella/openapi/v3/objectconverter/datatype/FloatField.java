package de.mcella.openapi.v3.objectconverter.datatype;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class FloatField implements StandardField {

  @Override
  public void addField(Field field, Map<String, Object> properties) {
    addField(field.getName(), properties);
  }

  @Override
  public void addField(String fieldName, Map<String, Object> properties) {
    Map<String, Object> fieldProperties = new LinkedHashMap<>();
    addType(fieldProperties);
    properties.put(fieldName, fieldProperties);
  }

  @Override
  public void addType(Map<String, Object> properties) {
    properties.put("type", "number");
    properties.put("format", "float");
  }
}
