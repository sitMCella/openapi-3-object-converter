package de.mcella.openapi.v3.objectconverter;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectConverter {

  private final ConverterService converterService;

  ObjectConverter(ConverterService converterService) {
    this.converterService = converterService;
  }

  public void convertObject(String typeName, Map<String, Object> objectProperties)
      throws ObjectConverterException {
    try {
      Class<?> clazz = Class.forName(typeName);
      objectProperties.put("type", "object");
      Map<String, Object> properties = new LinkedHashMap<>();
      for (Field field : clazz.getDeclaredFields()) {
        String fieldTypeName = field.getType().getCanonicalName();
        converterService.addField(field, fieldTypeName, properties);
      }
      objectProperties.put("properties", properties);
    } catch (ClassNotFoundException e) {
      throw new ObjectConverterException(
          String.format("Cannot convert class with name: %s", typeName), e);
    }
  }

  public void addItems(String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    Map<String, Object> objectProperties = new LinkedHashMap<>();
    convertObject(typeName, objectProperties);
    properties.put("items", objectProperties);
  }
}
