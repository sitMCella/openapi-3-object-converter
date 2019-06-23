package de.mcella.openapi.v3.objectconverter.datatype;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DoubleFieldTest {

  private static final String FIELD_NAME = "fieldName";

  private final Field field = mock(Field.class);

  private DoubleField doubleField;

  @Before
  public void setUp() {
    this.doubleField = new DoubleField();
  }

  @Test
  public void shouldAddDoubleFieldIntoPropertiesMap() {
    when(field.getName()).thenReturn(FIELD_NAME);
    Map<String, Object> properties = new HashMap<>();

    doubleField.addField(field, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey(FIELD_NAME));
    assertTrue(properties.get(FIELD_NAME) instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get(FIELD_NAME);
    assertThat(fieldProperties.size(), equalTo(2));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("number"));
    assertTrue(fieldProperties.containsKey("format"));
    assertThat(fieldProperties.get("format"), equalTo("double"));
  }

  @Test
  public void shouldAddDoubleFieldFromFieldNameIntoPropertiesMap() {
    Map<String, Object> properties = new HashMap<>();

    doubleField.addField(FIELD_NAME, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey(FIELD_NAME));
    assertTrue(properties.get(FIELD_NAME) instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get(FIELD_NAME);
    assertThat(fieldProperties.size(), equalTo(2));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("number"));
    assertTrue(fieldProperties.containsKey("format"));
    assertThat(fieldProperties.get("format"), equalTo("double"));
  }

  @Test
  public void shouldAddDoubleTypeIntoPropertiesMap() {
    Map<String, Object> properties = new HashMap<>();

    doubleField.addType(properties);

    assertThat(properties.size(), equalTo(2));
    assertTrue(properties.containsKey("type"));
    assertThat(properties.get("type"), equalTo("number"));
    assertTrue(properties.containsKey("format"));
    assertThat(properties.get("format"), equalTo("double"));
  }
}
