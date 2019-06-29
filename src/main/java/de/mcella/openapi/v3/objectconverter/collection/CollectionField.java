package de.mcella.openapi.v3.objectconverter.collection;

import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

import java.lang.reflect.Field;
import java.util.Map;

public interface CollectionField {

  void addField(Field field, Map<String, Object> properties) throws ObjectConverterException;

  void addItem(String typeName, Map<String, Object> properties) throws ObjectConverterException;

  void addItems(String typeName, Map<String, Object> properties) throws ObjectConverterException;
}
