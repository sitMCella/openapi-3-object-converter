package de.mcella.openapi.v3.objectconverter;

import java.lang.reflect.Field;
import java.util.Map;

public interface ConverterService {

  Map<String, Object> convert(String typeName) throws ObjectConverterException;

  void convert(String typeName, Map<String, Object> properties) throws ObjectConverterException;

  void addField(Field field, String typeName, Map<String, Object> properties)
      throws ObjectConverterException;

  void addItems(String typeName, Map<String, Object> properties) throws ObjectConverterException;
}
