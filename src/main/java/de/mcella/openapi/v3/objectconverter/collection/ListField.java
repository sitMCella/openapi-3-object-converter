package de.mcella.openapi.v3.objectconverter.collection;

import de.mcella.openapi.v3.objectconverter.ObjectConverter;
import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class ListField implements CollectionField {

  private static final String LIST_TYPE_NAME_PATTERN = "java.util.List<";

  @Override
  public void addField(Field field, Map<String, Object> properties, ObjectConverter objectConverter)
      throws ObjectConverterException {
    Map<String, Object> fieldProperties = new LinkedHashMap<>();
    fieldProperties.put("type", "array");
    String fieldTypeName = field.getGenericType().getTypeName();
    if (field.getGenericType() instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      if (actualTypeArguments.length == 1) {
        String valueType = actualTypeArguments[0].getTypeName();
        objectConverter.convertList(valueType, fieldProperties);
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
    properties.put(field.getName(), fieldProperties);
  }

  @Override
  public void addItem(
      String typeName, Map<String, Object> properties, ObjectConverter objectConverter)
      throws ObjectConverterException {
    String fieldTypeName =
        typeName.substring(LIST_TYPE_NAME_PATTERN.length(), typeName.length() - 1);
    properties.put("type", "array");
    objectConverter.convertList(fieldTypeName, properties);
  }

  @Override
  public void addItems(
      String typeName, Map<String, Object> properties, ObjectConverter objectConverter)
      throws ObjectConverterException {
    String fieldTypeName =
        typeName.substring(LIST_TYPE_NAME_PATTERN.length(), typeName.length() - 1);
    Map<String, Object> fieldProperties = new LinkedHashMap<>();
    fieldProperties.put("type", "array");
    objectConverter.convertList(fieldTypeName, fieldProperties);
    properties.put("items", fieldProperties);
  }
}
