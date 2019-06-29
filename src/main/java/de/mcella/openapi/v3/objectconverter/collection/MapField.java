package de.mcella.openapi.v3.objectconverter.collection;

import de.mcella.openapi.v3.objectconverter.ConverterService;
import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapField implements CollectionField {

  private static final String DICTIONARY_PATTERN = "java.util.Map<java.lang.String, ";

  private final ConverterService converterService;

  public MapField(ConverterService converterService) {
    this.converterService = converterService;
  }

  @Override
  public void addField(Field field, Map<String, Object> properties)
      throws ObjectConverterException {
    Map<String, Object> fieldProperties = new LinkedHashMap<>();
    fieldProperties.put("type", "object");
    Map<String, Object> additionalProperties = new LinkedHashMap<>();
    String fieldTypeName = field.getGenericType().getTypeName();
    if (field.getGenericType() instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      if (actualTypeArguments.length == 2) {
        if (!isDictionary(actualTypeArguments)) {
          throw new ObjectConverterException(
              String.format("Cannot convert object map field with type: %s", fieldTypeName));
        }
        String valueType = actualTypeArguments[1].getTypeName();
        converterService.convert(valueType, additionalProperties);
      } else if (actualTypeArguments.length == 0) {
        throw new ObjectConverterException(
            String.format("Cannot convert from nested parametrised: %s", fieldTypeName));
      } else {
        throw new ObjectConverterException(
            String.format("Cannot convert object field with type: %s", fieldTypeName));
      }
    } else {
      throw new ObjectConverterException(
          String.format("Cannot convert object field with type: %s", fieldTypeName));
    }
    fieldProperties.put("additionalProperties", additionalProperties);
    properties.put(field.getName(), fieldProperties);
  }

  @Override
  public void addItem(String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    if (!typeName.startsWith(DICTIONARY_PATTERN)) {
      throw new ObjectConverterException(
          String.format("Cannot convert object map field with type: %s", typeName));
    }
    String valueTypeName = getValueTypeName(typeName);
    Map<String, Object> additionalProperties = new LinkedHashMap<>();
    converterService.convert(valueTypeName, additionalProperties);
    properties.put("type", "object");
    properties.put("additionalProperties", additionalProperties);
  }

  @Override
  public void addItems(String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    if (!typeName.startsWith(DICTIONARY_PATTERN)) {
      throw new ObjectConverterException(
          String.format("Cannot convert object map field with type: %s", typeName));
    }
    String valueTypeName = getValueTypeName(typeName);
    Map<String, Object> additionalProperties = new LinkedHashMap<>();
    converterService.convert(valueTypeName, additionalProperties);
    Map<String, Object> fieldProperties = new LinkedHashMap<>();
    fieldProperties.put("type", "object");
    fieldProperties.put("additionalProperties", additionalProperties);
    properties.put("items", fieldProperties);
  }

  private boolean isDictionary(Type[] actualTypeArguments) {
    return actualTypeArguments[0].getTypeName().equals("java.lang.String");
  }

  private String getValueTypeName(String typeName) {
    return typeName.substring(DICTIONARY_PATTERN.length(), typeName.length() - 1);
  }
}
