package de.mcella.openapi.v3.objectconverter.datatype;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

public class StandardDataTypesTest {

  private StandardDataTypes standardDataTypes;

  @Before
  public void setUp() {
    this.standardDataTypes = new StandardDataTypes();
  }

  @Test
  public void shouldGetStandardFieldFromStringTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.lang.String");
    assertTrue(standardField instanceof StringField);
  }

  @Test
  public void shouldGetStandardFieldFromIntegerWrapperTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.lang.Integer");
    assertTrue(standardField instanceof IntegerField);
  }

  @Test
  public void shouldGetStandardFieldFromIntegerPrimitiveTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("int");
    assertTrue(standardField instanceof IntegerField);
  }

  @Test
  public void shouldGetStandardFieldFromLongWrapperTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.lang.Long");
    assertTrue(standardField instanceof LongField);
  }

  @Test
  public void shouldGetStandardFieldFromLongPrimitiveTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("long");
    assertTrue(standardField instanceof LongField);
  }

  @Test
  public void shouldGetStandardFieldFromFloatWrapperTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.lang.Float");
    assertTrue(standardField instanceof FloatField);
  }

  @Test
  public void shouldGetStandardFieldFromFloatPrimitiveTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("float");
    assertTrue(standardField instanceof FloatField);
  }

  @Test
  public void shouldGetStandardFieldFromDoubleWrapperTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.lang.Double");
    assertTrue(standardField instanceof DoubleField);
  }

  @Test
  public void shouldGetStandardFieldFromDoublePrimitiveTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("double");
    assertTrue(standardField instanceof DoubleField);
  }

  @Test
  public void shouldGetStandardFieldFromByteWrapperTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.lang.Byte");
    assertTrue(standardField instanceof ByteField);
  }

  @Test
  public void shouldGetStandardFieldFromBytePrimitiveTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("byte");
    assertTrue(standardField instanceof ByteField);
  }

  @Test
  public void shouldGetStandardFieldFromBooleanWrapperTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.lang.Boolean");
    assertTrue(standardField instanceof BooleanField);
  }

  @Test
  public void shouldGetStandardFieldFromBooleanPrimitiveTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("boolean");
    assertTrue(standardField instanceof BooleanField);
  }

  @Test
  public void shouldGetStandardFieldFromLocalDateTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.time.LocalDate");
    assertTrue(standardField instanceof DateField);
  }

  @Test
  public void shouldGetStandardFieldFromOffsetDateTimeTypeName() throws ObjectConverterException {
    StandardField standardField = standardDataTypes.getStandardField("java.time.OffsetDateTime");
    assertTrue(standardField instanceof DateTimeField);
  }

  @Test(expected = ObjectConverterException.class)
  public void shouldThrowObjectConverterExceptionOnGetStandardFieldFromUnknownTypeName()
      throws ObjectConverterException {
    standardDataTypes.getStandardField("unknown.data.type");
  }
}
