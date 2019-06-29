package de.mcella.openapi.v3.objectconverter.collection;

import de.mcella.openapi.v3.objectconverter.ConverterService;
import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectionDataTypes {

  private final Map<CollectionDataType, CollectionField> collectionDataTypes;

  public CollectionDataTypes(ConverterService converterService) {
    Map<CollectionDataType, CollectionField> collectionDataTypes = new HashMap<>();
    collectionDataTypes.put(CollectionDataType.LIST, new ListField(converterService));
    collectionDataTypes.put(CollectionDataType.MAP, new MapField(converterService));
    this.collectionDataTypes = Collections.unmodifiableMap(collectionDataTypes);
  }

  public boolean isCollectionDataType(String typeName) {
    return CollectionDataType.isCollectionDataType(typeName);
  }

  public CollectionField getCollectionField(String typeName) throws ObjectConverterException {
    CollectionDataType collectionDataType = getCollectionDataType(typeName);
    CollectionField collectionField = collectionDataTypes.get(collectionDataType);
    if (collectionField == null) {
      throw new ObjectConverterException(
          String.format("Cannot find collection field from type name: %s", typeName));
    }
    return collectionField;
  }

  private CollectionDataType getCollectionDataType(String typeName)
      throws ObjectConverterException {
    try {
      return CollectionDataType.fromTypeName(typeName);
    } catch (CollectionDataTypeNotFoundException e) {
      throw new ObjectConverterException(
          String.format("Cannot find collection data type from type name: %s", typeName), e);
    }
  }
}
