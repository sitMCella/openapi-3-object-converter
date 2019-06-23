package de.mcella.openapi.v3.objectconverter;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.lang.reflect.Field;

import de.mcella.openapi.v3.objectconverter.collection.CollectionDataTypes;
import de.mcella.openapi.v3.objectconverter.datatype.LongField;
import de.mcella.openapi.v3.objectconverter.datatype.StandardDataTypes;
import de.mcella.openapi.v3.objectconverter.datatype.StringField;

public class ObjectConverterTest {

  private final StandardDataTypes standardDataTypes = mock(StandardDataTypes.class);
  private final CollectionDataTypes collectionDataTypes = mock(CollectionDataTypes.class);

  private ObjectConverter objectConverter;

  @Before
  public void setUp() {
    this.objectConverter = new ObjectConverter(standardDataTypes, collectionDataTypes);
  }

  @Test
  public void shouldCreateSchemaDefinitionForLongPrimitiveDataType()
      throws ObjectConverterException {
    LongField longField = mock(LongField.class);
    String typeName = "long";
    when(standardDataTypes.isPrimitiveDataType(typeName)).thenReturn(true);
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(true);
    when(standardDataTypes.getStandardField(typeName)).thenReturn(longField);

    objectConverter.convert(typeName);

    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> argument = ArgumentCaptor.forClass(Map.class);
    verify(longField).addType(argument.capture());
  }

  @Test(expected = ObjectConverterException.class)
  public void
      shouldThrowObjectConverterExceptionOnCreateSchemaDefinitionForNonStandardPrimitiveDataType()
          throws ObjectConverterException {
    when(standardDataTypes.isPrimitiveDataType("char")).thenReturn(true);
    when(standardDataTypes.isStandardDataType("char")).thenReturn(false);

    objectConverter.convert("char");
  }

  @Test
  public void shouldCreateSchemaDefinitionForLongWrapperDataType() throws ObjectConverterException {
    LongField longField = mock(LongField.class);
    String typeName = "java.lang.Long";
    when(standardDataTypes.isPrimitiveDataType(typeName)).thenReturn(false);
    when(standardDataTypes.isStandardDataType(java.lang.Long.class)).thenReturn(true);
    when(standardDataTypes.isStandardDataType(typeName)).thenReturn(true);
    when(standardDataTypes.getStandardField(typeName)).thenReturn(longField);

    objectConverter.convert(typeName);

    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> argument = ArgumentCaptor.forClass(Map.class);
    verify(longField).addType(argument.capture());
  }

  @Test
  public void shouldCreateSchemaDefinitionForObjectWithStringFieldDataType()
      throws ObjectConverterException {
    StringField stringField = mock(StringField.class);
    String typeName = "de.mcella.openapi.v3.objectconverter.PublicStringField";
    when(standardDataTypes.isPrimitiveDataType(typeName)).thenReturn(false);
    when(standardDataTypes.isStandardDataType(PublicStringField.class)).thenReturn(false);
    when(standardDataTypes.isStandardDataType("java.lang.String")).thenReturn(true);
    when(standardDataTypes.getStandardField("java.lang.String")).thenReturn(stringField);

    Map<String, Object> schemaDefinition = objectConverter.convert(typeName);

    assertThat(schemaDefinition.size(), equalTo(2));
    assertTrue(schemaDefinition.containsKey("type"));
    ArgumentCaptor<Field> fieldArgument = ArgumentCaptor.forClass(Field.class);
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> mapArgument = ArgumentCaptor.forClass(Map.class);
    verify(stringField).addField(fieldArgument.capture(), mapArgument.capture());
  }
}
