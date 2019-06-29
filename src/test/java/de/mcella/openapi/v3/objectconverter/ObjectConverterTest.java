package de.mcella.openapi.v3.objectconverter;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class ObjectConverterTest {

  private final ConverterService converterService = mock(ConverterService.class);

  private ObjectConverter objectConverter;

  @Before
  public void setUp() {
    this.objectConverter = new ObjectConverter(converterService);
  }

  @Test
  public void shouldConvertIntegerWrapperField() throws ObjectConverterException {
    String typeName = "de.mcella.openapi.v3.objectconverter.example.IntegerWrapperField";
    Map<String, Object> objectProperties = new HashMap<>();

    objectConverter.convertObject(typeName, objectProperties);

    verifyConversionOfInnerField();
  }

  @Test
  public void shouldCreateConvertedIntegerWrapperFieldItem() throws ObjectConverterException {
    String typeName = "de.mcella.openapi.v3.objectconverter.example.IntegerWrapperField";
    Map<String, Object> objectProperties = new HashMap<>();

    objectConverter.convertObject(typeName, objectProperties);

    verifyConvertedObjectProperties(objectProperties);
  }

  @Test
  public void shouldConvertIntegerWrapperFieldItem() throws ObjectConverterException {
    String typeName = "de.mcella.openapi.v3.objectconverter.example.IntegerWrapperField";
    Map<String, Object> properties = new HashMap<>();

    objectConverter.addItems(typeName, properties);

    verifyConversionOfInnerField();
  }

  @Test
  public void shouldCreateListOfConvertedIntegerWrapperFieldItems()
      throws ObjectConverterException {
    String typeName = "de.mcella.openapi.v3.objectconverter.example.IntegerWrapperField";
    Map<String, Object> properties = new HashMap<>();

    objectConverter.addItems(typeName, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey("items"));
    assertTrue(properties.get("items") instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> objectProperties = (Map<String, Object>) properties.get("items");
    verifyConvertedObjectProperties(objectProperties);
  }

  private void verifyConversionOfInnerField() throws ObjectConverterException {
    ArgumentCaptor<Field> field = ArgumentCaptor.forClass(Field.class);
    ArgumentCaptor<String> fieldTypeName = ArgumentCaptor.forClass(String.class);
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, Object>> properties = ArgumentCaptor.forClass(Map.class);
    verify(converterService)
        .addField(field.capture(), fieldTypeName.capture(), properties.capture());
    assertThat(fieldTypeName.getValue(), equalTo("java.lang.Integer"));
    assertTrue(properties.getValue().isEmpty());
  }

  private void verifyConvertedObjectProperties(Map<String, Object> objectProperties) {
    assertThat(objectProperties.size(), equalTo(2));
    assertTrue(objectProperties.containsKey("type"));
    assertThat(objectProperties.get("type"), equalTo("object"));
    assertTrue(objectProperties.containsKey("properties"));
    assertTrue(objectProperties.get("properties") instanceof java.util.Map);
  }
}
