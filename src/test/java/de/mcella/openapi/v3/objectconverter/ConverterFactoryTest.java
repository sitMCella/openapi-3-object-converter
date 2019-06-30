package de.mcella.openapi.v3.objectconverter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import de.mcella.openapi.v3.objectconverter.collection.CollectionDataTypes;
import de.mcella.openapi.v3.objectconverter.collection.CollectionField;
import de.mcella.openapi.v3.objectconverter.datatype.StandardDataTypes;
import de.mcella.openapi.v3.objectconverter.datatype.StandardField;

public class ConverterFactoryTest {

  private final ObjectConverter objectConverter = mock(ObjectConverter.class);
  private final CollectionDataTypes collectionDataTypes = mock(CollectionDataTypes.class);
  private final StandardDataTypes standardDataTypes = mock(StandardDataTypes.class);

  private ConverterFactory converterFactory;

  @Before
  public void setUp() {
    this.converterFactory =
        new ConverterFactory(objectConverter, collectionDataTypes, standardDataTypes);
  }

  @Test
  public void shouldConvertStandardDataTypeFromTypeName() throws ObjectConverterException {
    String typeName = "java.lang.String";
    Map<String, Object> properties = new HashMap<>();
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(true);
    StandardField standardField = mock(StandardField.class);
    when(standardDataTypes.getStandardField(typeName)).thenReturn(standardField);

    converterFactory.convert(typeName, properties);

    verify(standardDataTypes).getStandardField(typeName);
    verify(standardField).addType(properties);
  }

  @Test
  public void shouldConvertCollectionDataTypeFromTypeName() throws ObjectConverterException {
    String typeName = "java.util.List<java.lang.String>";
    Map<String, Object> properties = new HashMap<>();
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(false);
    when(collectionDataTypes.isCollectionDataType(typeName)).thenReturn(true);
    CollectionField collectionField = mock(CollectionField.class);
    when(collectionDataTypes.getCollectionField(typeName)).thenReturn(collectionField);

    converterFactory.convert(typeName, properties);

    verify(collectionDataTypes).getCollectionField(typeName);
    verify(collectionField).addItem(typeName, properties);
  }

  @Test
  public void shouldConvertObjectDataTypeFromTypeName() throws ObjectConverterException {
    String typeName = "de.mcella.openapi.v3.objectconverter.Example";
    Map<String, Object> properties = new HashMap<>();
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(false);
    when(collectionDataTypes.isCollectionDataType(typeName)).thenReturn(false);

    converterFactory.convert(typeName, properties);

    verify(objectConverter).convertObject(typeName, properties);
  }

  @Test
  public void shouldAddStandardDataTypeField() throws ObjectConverterException {
    Field field = mock(Field.class);
    String typeName = "java.lang.String";
    Map<String, Object> properties = new HashMap<>();
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(true);
    StandardField standardField = mock(StandardField.class);
    when(standardDataTypes.getStandardField(typeName)).thenReturn(standardField);

    converterFactory.addField(field, typeName, properties);

    verify(standardDataTypes).getStandardField(typeName);
    verify(standardField).addField(field, properties);
  }

  @Test
  public void shouldAddCollectionDataTypeField() throws ObjectConverterException {
    Field field = mock(Field.class);
    String typeName = "java.util.List<java.lang.String>";
    Map<String, Object> properties = new HashMap<>();
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(false);
    when(collectionDataTypes.isCollectionDataType(typeName)).thenReturn(true);
    CollectionField collectionField = mock(CollectionField.class);
    when(collectionDataTypes.getCollectionField(typeName)).thenReturn(collectionField);

    converterFactory.addField(field, typeName, properties);

    verify(collectionDataTypes).getCollectionField(typeName);
    verify(collectionField).addField(field, properties);
  }

  @Test
  public void shouldAddCollectionDataTypeItems() throws ObjectConverterException {
    String typeName = "java.util.List<java.lang.String>";
    Map<String, Object> properties = new HashMap<>();
    when(collectionDataTypes.isCollectionDataType(typeName)).thenReturn(true);
    CollectionField collectionField = mock(CollectionField.class);
    when(collectionDataTypes.getCollectionField(typeName)).thenReturn(collectionField);

    converterFactory.addItems(typeName, properties);

    verify(collectionDataTypes).getCollectionField(typeName);
    verify(collectionField).addItems(typeName, properties);
  }

  @Test
  public void shouldAddStandardDataTypeItems() throws ObjectConverterException {
    String typeName = "java.lang.String";
    Map<String, Object> properties = new HashMap<>();
    when(collectionDataTypes.isCollectionDataType(typeName)).thenReturn(false);
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(true);

    converterFactory.addItems(typeName, properties);

    verify(standardDataTypes).addItems(typeName, properties);
  }

  @Test
  public void shouldAddObjectDataTypeItems() throws ObjectConverterException {
    String typeName = "de.mcella.openapi.v3.objectconverter.Example";
    Map<String, Object> properties = new HashMap<>();
    when(collectionDataTypes.isCollectionDataType(typeName)).thenReturn(false);
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(false);

    converterFactory.addItems(typeName, properties);

    verify(objectConverter).addItems(typeName, properties);
  }
}
