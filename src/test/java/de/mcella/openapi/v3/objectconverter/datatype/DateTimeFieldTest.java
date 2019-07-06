package de.mcella.openapi.v3.objectconverter.datatype;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class DateTimeFieldTest {

  private static final String FIELD_NAME = "fieldName";

  private final Field field = mock(Field.class);

  private DateTimeField dateTimeField;

  @Before
  public void setUp() {
    this.dateTimeField = new DateTimeField();
  }

  @Test
  public void shouldAddDateTimeFieldIntoPropertiesMap() {
    when(field.getName()).thenReturn(FIELD_NAME);
    Map<String, Object> properties = new HashMap<>();

    dateTimeField.addField(field, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey(FIELD_NAME));
    assertTrue(properties.get(FIELD_NAME) instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get(FIELD_NAME);
    assertThat(fieldProperties.size(), equalTo(2));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("string"));
    assertTrue(fieldProperties.containsKey("format"));
    assertThat(fieldProperties.get("format"), equalTo("date-time"));
  }

  @Test
  public void shouldAddDateTimeFieldFromFieldNameIntoPropertiesMap() {
    Map<String, Object> properties = new HashMap<>();

    dateTimeField.addField(FIELD_NAME, properties);

    assertThat(properties.size(), equalTo(1));
    assertTrue(properties.containsKey(FIELD_NAME));
    assertTrue(properties.get(FIELD_NAME) instanceof java.util.Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> fieldProperties = (Map<String, Object>) properties.get(FIELD_NAME);
    assertThat(fieldProperties.size(), equalTo(2));
    assertTrue(fieldProperties.containsKey("type"));
    assertThat(fieldProperties.get("type"), equalTo("string"));
    assertTrue(fieldProperties.containsKey("format"));
    assertThat(fieldProperties.get("format"), equalTo("date-time"));
  }

  @Test
  public void shouldAddDateTimeTypeIntoPropertiesMap() {
    Map<String, Object> properties = new HashMap<>();

    dateTimeField.addType(properties);

    assertThat(properties.size(), equalTo(2));
    assertTrue(properties.containsKey("type"));
    assertThat(properties.get("type"), equalTo("string"));
    assertTrue(properties.containsKey("format"));
    assertThat(properties.get("format"), equalTo("date-time"));
  }
}
