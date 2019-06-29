package de.mcella.openapi.v3.objectconverter;

import de.mcella.openapi.v3.objectconverter.collection.CollectionDataTypes;
import de.mcella.openapi.v3.objectconverter.collection.CollectionField;
import de.mcella.openapi.v3.objectconverter.datatype.StandardDataTypes;
import de.mcella.openapi.v3.objectconverter.datatype.StandardField;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConverterFactory implements ConverterService {

  private static ConverterFactory converterFactory;

  private final ObjectConverter objectConverter;
  private final CollectionDataTypes collectionDataTypes;
  private final StandardDataTypes standardDataTypes;

  private ConverterFactory() {
    if (converterFactory != null) {
      throw new RuntimeException(
          "Use getInstance() method to get the single instance of this class");
    }
    objectConverter = new ObjectConverter(this);
    collectionDataTypes = new CollectionDataTypes(this);
    standardDataTypes = new StandardDataTypes();
  }

  public static ConverterFactory getInstance() {
    if (converterFactory == null) {
      converterFactory = new ConverterFactory();
    }
    return converterFactory;
  }

  @Override
  public Map<String, Object> convert(String typeName) throws ObjectConverterException {
    Map<String, Object> schemaDefinition = new LinkedHashMap<>();
    getInstance().convert(typeName, schemaDefinition);
    return schemaDefinition;
  }

  @Override
  public void convert(String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    if (standardDataTypes.isStandardDataType(typeName)) {
      StandardField standardField = standardDataTypes.getStandardField(typeName);
      standardField.addType(properties);
      return;
    }
    if (collectionDataTypes.isCollectionDataType(typeName)) {
      CollectionField collectionField = collectionDataTypes.getCollectionField(typeName);
      collectionField.addItem(typeName, properties);
      return;
    }
    objectConverter.convertObject(typeName, properties);
  }

  @Override
  public void addField(Field field, String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    if (standardDataTypes.isStandardDataType(typeName)) {
      StandardField standardField = standardDataTypes.getStandardField(typeName);
      standardField.addField(field, properties);
      return;
    }
    if (collectionDataTypes.isCollectionDataType(typeName)) {
      CollectionField collectionField = collectionDataTypes.getCollectionField(typeName);
      collectionField.addField(field, properties);
    }
  }

  @Override
  public void addItems(String typeName, Map<String, Object> properties)
      throws ObjectConverterException {
    if (collectionDataTypes.isCollectionDataType(typeName)) {
      CollectionField collectionField = collectionDataTypes.getCollectionField(typeName);
      collectionField.addItems(typeName, properties);
      return;
    }
    if (standardDataTypes.isStandardDataType(typeName)) {
      standardDataTypes.addItems(typeName, properties);
      return;
    }
    objectConverter.addItems(typeName, properties);
  }
}
