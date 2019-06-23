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

public class BooleanFieldTest {

  private static final String FIELD_NAME = "fieldName";

  private final Field field = mock(Field.class);

  private BooleanField booleanField;

  @Before
  public void setUp() {
    this.booleanField = new BooleanField();
  }

  @Test
  public void shouldAddBooleanFieldIntoPropertiesMap() {
    when(field.getName()).thenReturn(FIELD_NAME);
    Map<String, Object> properties = new HashMap<>();

    booleanField.addField(field, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey(FIELD_NAME));
    assertTrue(properties.get(FIELD_NAME) instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get(FIELD_NAME);
    assertThat(fieldProperties.size(), equalTo(1));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("boolean"));
  }

  @Test
  public void shouldAddBooleanFieldFromFieldNameIntoPropertiesMap() {
    Map<String, Object> properties = new HashMap<>();

    booleanField.addField(FIELD_NAME, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey(FIELD_NAME));
    assertTrue(properties.get(FIELD_NAME) instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get(FIELD_NAME);
    assertThat(fieldProperties.size(), equalTo(1));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("boolean"));
  }

  @Test
  public void shouldAddBooleanTypeIntoPropertiesMap() {
    Map<String, Object> properties = new HashMap<>();

    booleanField.addType(properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey("type"));
    assertThat(properties.get("type"), equalTo("boolean"));
  }
}
