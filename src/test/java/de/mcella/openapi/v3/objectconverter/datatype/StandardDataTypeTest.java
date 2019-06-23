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
  public void shouldStringDataTypeNotBePrimitive() {
    assertFalse(StandardDataType.STRING.isPrimitive());
  }

  @Test
  public void shouldGetIntegerWrapperTypeName() {
    String typeName = StandardDataType.INTEGER_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Integer"));
  }

  @Test
  public void shouldIntegerWrapperDataTypeNotBePrimitive() {
    assertFalse(StandardDataType.INTEGER_WRAPPER.isPrimitive());
  }

  @Test
  public void shouldGetIntegerPrimitiveTypeName() {
    String typeName = StandardDataType.INTEGER_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("int"));
  }

  @Test
  public void shouldIntegerPrimitiveDataTypeBePrimitive() {
    assertTrue(StandardDataType.INTEGER_PRIMITIVE.isPrimitive());
  }

  @Test
  public void shouldGetLongWrapperTypeName() {
    String typeName = StandardDataType.LONG_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Long"));
  }

  @Test
  public void shouldLongWrapperDataTypeNotBePrimitive() {
    assertFalse(StandardDataType.LONG_WRAPPER.isPrimitive());
  }

  @Test
  public void shouldGetLongPrimitiveTypeName() {
    String typeName = StandardDataType.LONG_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("long"));
  }

  @Test
  public void shouldLongPrimitiveDataTypeBePrimitive() {
    assertTrue(StandardDataType.LONG_PRIMITIVE.isPrimitive());
  }

  @Test
  public void shouldGetFloatWrapperTypeName() {
    String typeName = StandardDataType.FLOAT_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Float"));
  }

  @Test
  public void shouldFloatWrapperDataTypeNotBePrimitive() {
    assertFalse(StandardDataType.FLOAT_WRAPPER.isPrimitive());
  }

  @Test
  public void shouldGetFloatPrimitiveTypeName() {
    String typeName = StandardDataType.FLOAT_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("float"));
  }

  @Test
  public void shouldFloatPrimitiveDataTypeBePrimitive() {
    assertTrue(StandardDataType.FLOAT_PRIMITIVE.isPrimitive());
  }

  @Test
  public void shouldGetDoubleWrapperTypeName() {
    String typeName = StandardDataType.DOUBLE_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Double"));
  }

  @Test
  public void shouldDoubleWrapperDataTypeNotBePrimitive() {
    assertFalse(StandardDataType.DOUBLE_WRAPPER.isPrimitive());
  }

  @Test
  public void shouldGetDoublePrimitiveTypeName() {
    String typeName = StandardDataType.DOUBLE_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("double"));
  }

  @Test
  public void shouldDoublePrimitiveDataTypeBePrimitive() {
    assertTrue(StandardDataType.DOUBLE_PRIMITIVE.isPrimitive());
  }

  @Test
  public void shouldGetByteWrapperTypeName() {
    String typeName = StandardDataType.BYTE_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Byte"));
  }

  @Test
  public void shouldByteWrapperDataTypeNotBePrimitive() {
    assertFalse(StandardDataType.BYTE_WRAPPER.isPrimitive());
  }

  @Test
  public void shouldGetBytePrimitiveTypeName() {
    String typeName = StandardDataType.BYTE_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("byte"));
  }

  @Test
  public void shouldBytePrimitiveDataTypeBePrimitive() {
    assertTrue(StandardDataType.BYTE_PRIMITIVE.isPrimitive());
  }

  @Test
  public void shouldGetBooleanWrapperTypeName() {
    String typeName = StandardDataType.BOOLEAN_WRAPPER.getTypeName();
    assertThat(typeName, equalTo("java.lang.Boolean"));
  }

  @Test
  public void shouldBooleanWrapperDataTypeNotBePrimitive() {
    assertFalse(StandardDataType.BYTE_WRAPPER.isPrimitive());
  }

  @Test
  public void shouldGetBooleanPrimitiveTypeName() {
    String typeName = StandardDataType.BOOLEAN_PRIMITIVE.getTypeName();
    assertThat(typeName, equalTo("boolean"));
  }

  @Test
  public void shouldBooleanPrimitiveDataTypeBePrimitive() {
    assertTrue(StandardDataType.BOOLEAN_PRIMITIVE.isPrimitive());
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
  public void shouldUnknownTypeNameNotBeAStandardDataType() {
    assertFalse(StandardDataType.isStandardDataType("unknown.data.type"));
  }

  @Test
  public void shouldStringClassBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType(java.lang.String.class));
  }

  @Test
  public void shouldIntegerWrapperClassBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType(java.lang.Integer.class));
  }

  @Test
  public void shouldLongWrapperClassBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType(java.lang.Long.class));
  }

  @Test
  public void shouldFloatWrapperClassBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType(java.lang.Float.class));
  }

  @Test
  public void shouldDoubleWrapperClassBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType(java.lang.Double.class));
  }

  @Test
  public void shouldByteWrapperClassBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType(java.lang.Byte.class));
  }

  @Test
  public void shouldBooleanWrapperClassBeAStandardDataType() {
    assertTrue(StandardDataType.isStandardDataType(java.lang.Boolean.class));
  }

  @Test
  public void shouldListClassNotBeAStandardDataType() {
    assertFalse(StandardDataType.isStandardDataType(java.util.List.class));
  }

  @Test
  public void shouldMapClassNotBeAStandardDataType() {
    assertFalse(StandardDataType.isStandardDataType(java.util.Map.class));
  }

  @Test
  public void shouldStringTypeNameNotBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.String"));
  }

  @Test
  public void shouldIntegerWrapperTypeNameNotBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Integer"));
  }

  @Test
  public void shouldIntegerPrimitiveTypeNameBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("int"));
  }

  @Test
  public void shouldLongWrapperTypeNameNotBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Long"));
  }

  @Test
  public void shouldLongPrimitiveTypeNameBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("long"));
  }

  @Test
  public void shouldFloatWrapperTypeNameNotBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Float"));
  }

  @Test
  public void shouldFloatPrimitiveTypeNameBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("float"));
  }

  @Test
  public void shouldDoubleWrapperTypeNameNotBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Double"));
  }

  @Test
  public void shouldDoublePrimitiveTypeNameBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("double"));
  }

  @Test
  public void shouldByteWrapperTypeNameNotBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Byte"));
  }

  @Test
  public void shouldBytePrimitiveTypeNameBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("byte"));
  }

  @Test
  public void shouldBooleanWrapperTypeNameNotBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("java.lang.Boolean"));
  }

  @Test
  public void shouldBooleanPrimitiveTypeNameBeAPrimitiveDataType() {
    assertTrue(StandardDataType.isStandardDataType("boolean"));
  }

  @Test
  public void shouldUnknownTypeNameNotBeAPrimitiveDataType() {
    assertFalse(StandardDataType.isStandardDataType("unknown.data.type"));
  }

  @Test
  public void shouldListTypeNameNotBeAPrimitiveDataType() {
    assertFalse(StandardDataType.isStandardDataType("java.util.List"));
  }

  @Test
  public void shouldMapTypeNameNotBeAPrimitiveDataType() {
    assertFalse(StandardDataType.isStandardDataType("java.util.Map"));
  }
}