package de.mcella.openapi.v3.objectconverter.collection;

import java.lang.reflect.Field;
import java.util.Map;

import de.mcella.openapi.v3.objectconverter.ObjectConverter;
import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

public interface CollectionField {

  void addField(Field field, Map<String, Object> properties, ObjectConverter objectConverter)
      throws ObjectConverterException;

  void addItems(String typeName, Map<String, Object> properties, ObjectConverter objectConverter)
      throws ObjectConverterException;
}
