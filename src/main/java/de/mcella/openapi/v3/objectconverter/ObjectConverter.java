package de.mcella.openapi.v3.objectconverter;

import de.mcella.openapi.v3.objectconverter.collection.CollectionDataTypes;
import de.mcella.openapi.v3.objectconverter.collection.CollectionField;
import de.mcella.openapi.v3.objectconverter.datatype.*;

import java.lang.reflect.Field;
import java.util.*;

public class ObjectConverter {

  private final StandardDataTypes standardDataTypes;
  private final CollectionDataTypes collectionDataTypes;

  public ObjectConverter(
      StandardDataTypes standardDataTypes, CollectionDataTypes collectionDataTypes) {
    this.standardDataTypes = standardDataTypes;
    this.collectionDataTypes = collectionDataTypes;
  }

  public Map<String, Object> convert(String typeName) throws ObjectConverterException {
    Map<String, Object> schemaDefinition = new LinkedHashMap<>();
    convert(typeName, schemaDefinition);
    return schemaDefinition;
  }

  public void convert(String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    try {
      if (standardDataTypes.isPrimitiveDataType(typeName)) {
        insertStandardType(typeName, properties);
        return;
      }
      Class<?> clazz = Class.forName(typeName);
      convert(clazz, properties);
    } catch (ClassNotFoundException e) {
      throw new ObjectConverterException(
          String.format("Cannot convert class with name: %s", typeName), e);
    }
  }

  public void convertList(String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    try {
      if (collectionDataTypes.isCollectionDataType(typeName)) {
        CollectionField collectionField = collectionDataTypes.getCollectionField(typeName);
        collectionField.addItems(typeName, properties, this);
        return;
      }
      Class<?> clazz = Class.forName(typeName);
      if (standardDataTypes.isStandardDataType(clazz)) {
        convertFromStandardTypeName("items", clazz.getCanonicalName(), properties);
        return;
      }
      Map<String, Object> subProperties = new LinkedHashMap<>();
      convert(clazz, subProperties);
      properties.put("items", subProperties);
    } catch (ClassNotFoundException e) {
      throw new ObjectConverterException(
          String.format("Cannot convert object field with type: %s", typeName), e);
    }
  }

  private void convert(Class<?> clazz, Map<String, Object> schemaDefinition)
      throws ObjectConverterException {
    if (standardDataTypes.isStandardDataType(clazz)) {
      insertStandardType(clazz.getCanonicalName(), schemaDefinition);
      return;
    }
    convertObject(clazz, schemaDefinition);
  }

  private void convertObject(Class<?> clazz, Map<String, Object> schemaDefinition)
      throws ObjectConverterException {
    schemaDefinition.put("type", "object");
    Map<String, Object> properties = new LinkedHashMap<>();
    for (Field field : clazz.getDeclaredFields()) {
      String typeName = field.getType().getCanonicalName();
      if (standardDataTypes.isStandardDataType(typeName)) {
        StandardField standardField = standardDataTypes.getStandardField(typeName);
        standardField.addField(field, properties);
      } else if (collectionDataTypes.isCollectionDataType(typeName)) {
        CollectionField collectionField = collectionDataTypes.getCollectionField(typeName);
        collectionField.addField(field, properties, this);
      } else {
        throw new ObjectConverterException(
            String.format("Cannot convert object field with type: %s", typeName));
      }
    }
    schemaDefinition.put("properties", properties);
  }

  private void insertStandardType(String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    if (standardDataTypes.isStandardDataType(typeName)) {
      StandardField standardField = standardDataTypes.getStandardField(typeName);
      standardField.addType(properties);
    } else {
      throw new ObjectConverterException(
          String.format("Cannot convert object field with non standard type: %s", typeName));
    }
  }

  private void convertFromStandardTypeName(
      String fieldName, String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    if (standardDataTypes.isStandardDataType(typeName)) {
      StandardField standardField = standardDataTypes.getStandardField(typeName);
      standardField.addField(fieldName, properties);
    } else {
      throw new ObjectConverterException(
          String.format("Cannot convert object field with type: %s", typeName));
    }
  }
}
