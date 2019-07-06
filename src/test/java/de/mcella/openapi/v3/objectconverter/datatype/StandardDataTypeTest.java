package de.mcella.openapi.v3.objectconverter.datatype;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class StandardDataTypeTest {

  @Test
  public void shouldGetStringTypeName() {
    String typeName = StandardDataType.STRING.getTypeName();
    assertThat(typeName, equalTo("java.lang.String"));
  }

  @Test
  public void shouldGetIntegerWrapperTypeName() {
    String typeName = StandardDataType.INTEGER_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Integer"));
  }

  @Test
  public void shouldGetIntegerPrimitiveTypeName() {
    String typeName = StandardDataType.INTEGER_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("int"));
  }

  @Test
  public void shouldGetLongWrapperTypeName() {
    String typeName = StandardDataType.LONG_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Long"));
  }

  @Test
  public void shouldGetLongPrimitiveTypeName() {
    String typeName = StandardDataType.LONG_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("long"));
  }

  @Test
  public void shouldGetFloatWrapperTypeName() {
    String typeName = StandardDataType.FLOAT_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Float"));
  }

  @Test
  public void shouldGetFloatPrimitiveTypeName() {
    String typeName = StandardDataType.FLOAT_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("float"));
  }

  @Test
  public void shouldGetDoubleWrapperTypeName() {
    String typeName = StandardDataType.DOUBLE_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Double"));
  }

  @Test
  public void shouldGetDoublePrimitiveTypeName() {
    String typeName = StandardDataType.DOUBLE_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("double"));
  }

  @Test
  public void shouldGetByteWrapperTypeName() {
    String typeName = StandardDataType.BYTE_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Byte"));
  }

  @Test
  public void shouldGetBytePrimitiveTypeName() {
    String typeName = StandardDataType.BYTE_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("byte"));
  }

  @Test
  public void shouldGetBooleanWrapperTypeName() {
    String typeName = StandardDataType.BOOLEAN_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Boolean"));
  }

  @Test
  public void shouldGetBooleanPrimitiveTypeName() {
    String typeName = StandardDataType.BOOLEAN_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("boolean"));
  }
  
  @Test
  public void shouldGetLocalDateTypeName() {
    String typeName = StandardDataType.LOCAL_DATE.getTypeName();
    assertThat(typeName, equalTo("java.time.LocalDate"));
  }

  @Test
  public void shouldGetStringStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("java.lang.String");
    assertThat(standardDataType, equalTo(StandardDataType.STRING));
  }

  @Test
  public void shouldGetIntegerWrapperStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("java.lang.Integer");
    assertThat(standardDataType, equalTo(StandardDataType.INTEGER_WRAPPER));
  }

  @Test
  public void shouldGetIntegerPrimitiveStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("int");
    assertThat(standardDataType, equalTo(StandardDataType.INTEGER_PRIMITIVE));
  }

  @Test
  public void shouldGetLongWrapperStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("java.lang.Long");
    assertThat(standardDataType, equalTo(StandardDataType.LONG_WRAPPER));
  }

  @Test
  public void shouldGetLongPrimitiveStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("long");
    assertThat(standardDataType, equalTo(StandardDataType.LONG_PRIMITIVE));
  }

  @Test
  public void shouldGetFloatWrapperStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("java.lang.Float");
    assertThat(standardDataType, equalTo(StandardDataType.FLOAT_WRAPPER));
  }

  @Test
  public void shouldGetFloatPrimitiveStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("float");
    assertThat(standardDataType, equalTo(StandardDataType.FLOAT_PRIMITIVE));
  }

  @Test
  public void shouldGetDoubleWrapperStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("java.lang.Double");
    assertThat(standardDataType, equalTo(StandardDataType.DOUBLE_WRAPPER));
  }

  @Test
  public void shouldGetDoublePrimitiveStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("double");
    assertThat(standardDataType, equalTo(StandardDataType.DOUBLE_PRIMITIVE));
  }

  @Test
  public void shouldGetByteWrapperStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("java.lang.Byte");
    assertThat(standardDataType, equalTo(StandardDataType.BYTE_WRAPPER));
  }

  @Test
  public void shouldGetBytePrimitiveStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("byte");
    assertThat(standardDataType, equalTo(StandardDataType.BYTE_PRIMITIVE));
  }

  @Test
  public void shouldGetBooleanWrapperStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("java.lang.Boolean");
    assertThat(standardDataType, equalTo(StandardDataType.BOOLEAN_WRAPPER));
  }

  @Test
  public void shouldGetBooleanPrimitiveStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("boolean");
    assertThat(standardDataType, equalTo(StandardDataType.BOOLEAN_PRIMITIVE));
  }
  
  @Test
  public void shouldGetLocalDateStandardDataTypeFromTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType standardDataType = StandardDataType.fromTypeName("java.time.LocalDate");
    assertThat(standardDataType, equalTo(StandardDataType.LOCAL_DATE));
  }

  @Test(expected = StandardDataTypeNotFoundException.class)
  public void shouldThrowStandardDataTypeNotFoundExceptionOnGetStandardDataTypeFromUnknownTypeName()
      throws StandardDataTypeNotFoundException {
    StandardDataType.fromTypeName("unknown.data.type");
  }

  @Test
  public void shouldStringTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.String"));
  }

  @Test
  public void shouldIntegerWrapperTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Integer"));
  }

  @Test
  public void shouldIntegerPrimitiveTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("int"));
  }

  @Test
  public void shouldLongWrapperTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Long"));
  }

  @Test
  public void shouldLongPrimitiveTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("long"));
  }

  @Test
  public void shouldFloatWrapperTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Float"));
  }

  @Test
  public void shouldFloatPrimitiveTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("float"));
  }

  @Test
  public void shouldDoubleWrapperTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Double"));
  }

  @Test
  public void shouldDoublePrimitiveTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("double"));
  }

  @Test
  public void shouldByteWrapperTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Byte"));
  }

  @Test
  public void shouldBytePrimitiveTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("byte"));
  }

  @Test
  public void shouldBooleanWrapperTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Boolean"));
  }

  @Test
  public void shouldBooleanPrimitiveTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("boolean"));
  }
  
  @Test
  public void shouldLocalDateTypeNameBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.time.LocalDate"));
  }

  @Test
  public void shouldUnknownTypeNameNotBeAStandardDataType() {
    assertFalse(StandardDataType.isStandardDataType("unknown.data.type"));
  }
}
