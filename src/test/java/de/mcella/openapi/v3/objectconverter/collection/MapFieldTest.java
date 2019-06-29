package de.mcella.openapi.v3.objectconverter.collection;

import org.junit.Before;
import org.junit.Test;

import de.mcella.openapi.v3.objectconverter.ConverterService;
import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MapFieldTest {

  private final ConverterService converterService = mock(ConverterService.class);

  private MapField mapField;
  private Map<String, Object> properties;

  @Before
  public void setUp() {
    this.mapField = new MapField(converterService);
    this.properties = new HashMap<>();
  }

  @Test
  public void shouldConvertMapOfStringToStringField() throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(field.getName()).thenReturn("fieldName");
    when(type.getTypeName()).thenReturn("java.util.Map<java.lang.String, java.lang.String>");
    Type key = mock(Type.class);
    Type value = mock(Type.class);
    Type[] actualTypeArguments = new Type[] {key, value};
    when(type.getActualTypeArguments()).thenReturn(actualTypeArguments);
    when(key.getTypeName()).thenReturn("java.lang.String");
    when(value.getTypeName()).thenReturn("java.lang.String");

    mapField.addField(field, properties);

    verify(converterService).convert("java.lang.String", new LinkedHashMap<>());
  }

  @Test
  public void shouldCreateConvertedMapOfStringToStringFieldItem() throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(field.getName()).thenReturn("fieldName");
    when(type.getTypeName()).thenReturn("java.util.Map<java.lang.String, java.lang.String>");
    Type key = mock(Type.class);
    Type value = mock(Type.class);
    Type[] actualTypeArguments = new Type[] {key, value};
    when(type.getActualTypeArguments()).thenReturn(actualTypeArguments);
    when(key.getTypeName()).thenReturn("java.lang.String");
    when(value.getTypeName()).thenReturn("java.lang.String");

    mapField.addField(field, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey("fieldName"));
    assertTrue(properties.get("fieldName") instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get("fieldName");
    assertThat(fieldProperties.size(), equalTo(2));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("object"));
    assertTrue(fieldProperties.containsKey("additionalProperties"));
  }

  @Test(expected = ObjectConverterException.class)
  public void shouldThrowObjectConverterExceptionOnAddFieldFromNonParameterizedTypeField()
      throws ObjectConverterException {
    Field field = mock(Field.class);
    Type type = mock(Type.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.Map<java.lang.String, java.lang.String>");

    mapField.addField(field, properties);
  }

  @Test(expected = ObjectConverterException.class)
  public void
      shouldThrowObjectConverterExceptionOnAddFieldFromParameterizedTypeFieldWithZeroTypeArgumentsCount()
          throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.Map<java.lang.String, java.lang.String>");
    when(type.getActualTypeArguments()).thenReturn(new Type[0]);

    mapField.addField(field, properties);
  }

  @Test(expected = ObjectConverterException.class)
  public void
      shouldThrowObjectConverterExceptionOnAddFieldFromParameterizedTypeFieldWithOneTypeArgumentsCount()
          throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.Map<java.lang.String, java.lang.String>");
    Type key = mock(Type.class);
    when(type.getActualTypeArguments()).thenReturn(new Type[] {key});

    mapField.addField(field, properties);
  }

  @Test
  public void shouldConvertMapOfStringToStringFieldOnAddItem() throws ObjectConverterException {
    String typeName = "java.util.Map<java.lang.String, java.lang.String>";

    mapField.addItem(typeName, properties);

    verify(converterService).convert("java.lang.String", new LinkedHashMap<>());
  }

  @Test
  public void shouldCreateConvertedMapOfStringToStringItemOnAddItem()
      throws ObjectConverterException {
    String typeName = "java.util.Map<java.lang.String, java.lang.String>";

    mapField.addItem(typeName, properties);

    verifyMapItemFieldProperties(properties);
  }

  @Test(expected = ObjectConverterException.class)
  public void shouldThrowObjectConverterExceptionOnAddItemForNonDictionaryMapType()
      throws ObjectConverterException {
    String typeName = "java.util.Map<java.lang.Long, java.lang.String>";

    mapField.addItem(typeName, properties);
  }

  @Test
  public void shouldConvertMapOfStringToStringFieldOnAddItems() throws ObjectConverterException {
    String typeName = "java.util.Map<java.lang.String, java.lang.String>";

    mapField.addItems(typeName, properties);

    verify(converterService).convert("java.lang.String", new LinkedHashMap<>());
  }

  @Test
  public void shouldCreateConvertedMapOfStringToStringItemOnAddItems()
      throws ObjectConverterException {
    String typeName = "java.util.Map<java.lang.String, java.lang.String>";

    mapField.addItems(typeName, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey("items"));
    assertTrue(properties.get("items") instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get("items");
    verifyMapItemFieldProperties(fieldProperties);
  }

  @Test(expected = ObjectConverterException.class)
  public void shouldThrowObjectConverterExceptionOnAddItemsForNonDictionaryMapType()
      throws ObjectConverterException {
    String typeName = "java.util.Map<java.lang.Long, java.lang.String>";

    mapField.addItems(typeName, properties);
  }

  private void verifyMapItemFieldProperties(Map<String, Object> fieldProperties) {
    assertThat(fieldProperties.size(), equalTo(2));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("object"));
    assertTrue(fieldProperties.containsKey("additionalProperties"));
    assertTrue(fieldProperties.get("additionalProperties") instanceof java.util.Map);
  }
}
