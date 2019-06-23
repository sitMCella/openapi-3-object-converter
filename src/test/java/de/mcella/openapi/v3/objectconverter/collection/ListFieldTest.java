package de.mcella.openapi.v3.objectconverter.collection;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;

import de.mcella.openapi.v3.objectconverter.ObjectConverter;
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

  private final ObjectConverter objectConverter = mock(ObjectConverter.class);

  private ListField listField;
  private Map<String, Object> properties;

  @Before
  public void setUp() {
    this.listField = new ListField();
    this.properties = new HashMap<>();
  }

  @Test
  public void shouldAddListFieldIntoPropertiesMap() throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(field.getName()).thenReturn("fieldName");
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");
    Type value = mock(Type.class);
    Type[] actualTypeArguments = new Type[] {value};
    when(type.getActualTypeArguments()).thenReturn(actualTypeArguments);
    when(value.getTypeName()).thenReturn("java.lang.String");

    listField.addField(field, properties, objectConverter);

    ArgumentCaptor<String> valueTypeArgument = ArgumentCaptor.forClass(String.class);
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> fieldPropertiesArgument =
        ArgumentCaptor.forClass(Map.class);
    verify(objectConverter)
        .convertList(valueTypeArgument.capture(), fieldPropertiesArgument.capture());
    assertThat(valueTypeArgument.getValue(), equalTo("java.lang.String"));
    Map<String, Object> fieldProperties1 = fieldPropertiesArgument.getValue();
    assertThat(fieldProperties1.size(), equalTo(1));
    assertTrue(fieldProperties1.containsKey("type"));
    assertThat(fieldProperties1.get("type"), equalTo("array"));
    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey("fieldName"));
    assertTrue(properties.get("fieldName") instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties2 = (Map<String, Object>) properties.get("fieldName");
    assertThat(fieldProperties2.size(), equalTo(1));
    assertTrue(fieldProperties2.containsKey("type"));
    assertThat(fieldProperties2.get("type"), equalTo("array"));
  }

  @Test(expected = ObjectConverterException.class)
  public void shouldThrowObjectConverterExceptionOnAddFieldFromNonParameterizedTypeField()
      throws ObjectConverterException {
    Field field = mock(Field.class);
    Type type = mock(Type.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");

    listField.addField(field, properties, objectConverter);
  }

  @Test(expected = ObjectConverterException.class)
  public void
      shouldThrowObjectConverterExceptionOnAddFieldFromParameterizedTypeFieldWithZeroActualTypeArgumentsCount()
          throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");
    when(type.getActualTypeArguments()).thenReturn(new Type[0]);

    listField.addField(field, properties, objectConverter);
  }

  @Test(expected = ObjectConverterException.class)
  public void
      shouldThrowObjectConverterExceptionOnAddFieldFromParameterizedTypeFieldWithTwoActualTypeArgumentsCount()
          throws ObjectConverterException {
    Field field = mock(Field.class);
    ParameterizedType type = mock(ParameterizedType.class);
    when(field.getGenericType()).thenReturn(type);
    when(type.getTypeName()).thenReturn("java.util.List<java.lang.String>");
    Type value = mock(Type.class);
    Type anotherValue = mock(Type.class);
    Type[] actualTypeArguments = new Type[] {value, anotherValue};
    when(type.getActualTypeArguments()).thenReturn(actualTypeArguments);

    listField.addField(field, properties, objectConverter);
  }

  @Test
  public void shouldAddListOfListItemsIntoPropertiesMap() throws ObjectConverterException {
    String typeName = "java.util.List<java.lang.String>";

    listField.addItems(typeName, properties, objectConverter);

    ArgumentCaptor<String> valueTypeArgument = ArgumentCaptor.forClass(String.class);
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> fieldPropertiesArgument =
        ArgumentCaptor.forClass(Map.class);
    verify(objectConverter)
        .convertList(valueTypeArgument.capture(), fieldPropertiesArgument.capture());
    assertThat(valueTypeArgument.getValue(), equalTo("java.lang.String"));
    Map<String, Object> fieldProperties1 = fieldPropertiesArgument.getValue();
    assertThat(fieldProperties1.size(), equalTo(1));
    assertTrue(fieldProperties1.containsKey("type"));
    assertThat(fieldProperties1.get("type"), equalTo("array"));
    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey("items"));
    assertTrue(properties.get("items") instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties2 = (Map<String, Object>) properties.get("items");
    assertThat(fieldProperties2.size(), equalTo(1));
    assertTrue(fieldProperties2.containsKey("type"));
    assertThat(fieldProperties2.get("type"), equalTo("array"));
  }
}
