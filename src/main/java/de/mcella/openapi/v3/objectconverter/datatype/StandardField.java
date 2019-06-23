package de.mcella.openapi.v3.objectconverter.datatype;

import java.lang.reflect.Field;
import java.util.Map;

public interface StandardField {

  void addField(Field field, Map<String, Object> properties);

  void addField(String fieldName, Map<String, Object> properties);

  void addType(Map<String, Object> properties);
}
