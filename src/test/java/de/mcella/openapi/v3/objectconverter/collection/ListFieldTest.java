package de.mcella.openapi.v3.objectconverter.collection;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;

import de.mcella.openapi.v3.objectconverter.ConverterService;
import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ListFieldTest {

  private final ConverterService converterService = mock(ConverterService.class);

  private ListField listField;
  private Map<String, Object> properties;

  @Before
  public void setUp() {
    this.listField = new ListField(converterService);
    this.properties = new HashMap<>();
  }

  @Test
  public void shouldConvertListOfStringField() throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(field.getName()).thenReturn("fieldName");
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");
    Type value = mock(Type.class);
    Type[] actualTypeArguments = new Type[] {value};
    when(type.getActualTypeArguments()).thenReturn(actualTypeArguments);
    when(value.getTypeName()).thenReturn("java.lang.String");

    listField.addField(field, properties);

    ArgumentCaptor<String> valueTypeArgument = ArgumentCaptor.forClass(String.class);
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> fieldPropertiesArgument =
        ArgumentCaptor.forClass(Map.class);
    verify(converterService)
        .addItems(valueTypeArgument.capture(), fieldPropertiesArgument.capture());
    assertThat(valueTypeArgument.getValue(), equalTo("java.lang.String"));
    verifyFieldProperties(fieldPropertiesArgument.getValue());
  }

  @Test
  public void shouldCreateConvertedListFieldItem() throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(field.getName()).thenReturn("fieldName");
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");
    Type value = mock(Type.class);
    Type[] actualTypeArguments = new Type[] {value};
    when(type.getActualTypeArguments()).thenReturn(actualTypeArguments);
    when(value.getTypeName()).thenReturn("java.lang.String");

    listField.addField(field, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey("fieldName"));
    assertTrue(properties.get("fieldName") instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get("fieldName");
    verifyFieldProperties(fieldProperties);
  }

  @Test(expected = ObjectConverterException.class)
  public void shouldThrowObjectConverterExceptionOnAddFieldFromNonParameterizedTypeField()
      throws ObjectConverterException {
    Field field = mock(Field.class);
    Type type = mock(Type.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");

    listField.addField(field, properties);
  }

  @Test(expected = ObjectConverterException.class)
  public void
      shouldThrowObjectConverterExceptionOnAddFieldFromParameterizedTypeFieldWithZeroTypeArgumentsCount()
          throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");
    when(type.getActualTypeArguments()).thenReturn(new Type[0]);

    listField.addField(field, properties);
  }

  @Test(expected = ObjectConverterException.class)
  public void
      shouldThrowObjectConverterExceptionOnAddFieldFromParameterizedTypeFieldWithTwoTypeArgumentsCount()
          throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");
    Type value = mock(Type.class);
    Type anotherValue = mock(Type.class);
    Type[] actualTypeArguments = new Type[] {value, anotherValue};
    when(type.getActualTypeArguments()).thenReturn(actualTypeArguments);

    listField.addField(field, properties);
  }

  @Test
  public void shouldConvertListOfStringFieldOnAddItem() throws ObjectConverterException {
    String typeName = "java.util.List<java.lang.String>";

    listField.addItem(typeName, properties);

    ArgumentCaptor<String> valueTypeArgument = ArgumentCaptor.forClass(String.class);
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> fieldPropertiesArgument =
        ArgumentCaptor.forClass(Map.class);
    verify(converterService)
        .addItems(valueTypeArgument.capture(), fieldPropertiesArgument.capture());
    assertThat(valueTypeArgument.getValue(), equalTo("java.lang.String"));
    verifyFieldProperties(fieldPropertiesArgument.getValue());
  }

  @Test
  public void shoulCreateConvertedListOfStringItemOnAddItem() throws ObjectConverterException {
    String typeName = "java.util.List<java.lang.String>";

    listField.addItem(typeName, properties);

    verifyFieldProperties(properties);
  }

  @Test
  public void shouldConvertListOfStringFieldOnAddItems() throws ObjectConverterException {
    String typeName = "java.util.List<java.lang.String>";

    listField.addItems(typeName, properties);

    ArgumentCaptor<String> valueTypeArgument = ArgumentCaptor.forClass(String.class);
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> fieldPropertiesArgument =
        ArgumentCaptor.forClass(Map.class);
    verify(converterService)
        .addItems(valueTypeArgument.capture(), fieldPropertiesArgument.capture());
    assertThat(valueTypeArgument.getValue(), equalTo("java.lang.String"));
    verifyFieldProperties(fieldPropertiesArgument.getValue());
  }

  @Test
  public void shouldCreateConvertedListOfStringItemOnAddItems() throws ObjectConverterException {
    String typeName = "java.util.List<java.lang.String>";

    listField.addItems(typeName, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey("items"));
    assertTrue(properties.get("items") instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get("items");
    verifyFieldProperties(fieldProperties);
  }

  private void verifyFieldProperties(Map<String, Object> fieldProperties) {
    assertThat(fieldProperties.size(), equalTo(1));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("array"));
  }
}
