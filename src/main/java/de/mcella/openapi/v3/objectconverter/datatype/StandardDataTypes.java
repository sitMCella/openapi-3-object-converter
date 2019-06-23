package de.mcella.openapi.v3.objectconverter.datatype;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

public class StandardDataTypes {

  private final Map<StandardDataType, StandardField> standardDataTypes;

  public StandardDataTypes() {
    IntegerField integerField = new IntegerField();
    LongField longField = new LongField();
    FloatField floatField = new FloatField();
    DoubleField doubleField = new DoubleField();
    ByteField byteField = new ByteField();
    BooleanField booleanField = new BooleanField();
    Map<StandardDataType, StandardField> standardDataTypes = new HashMap<>();
    standardDataTypes.put(StandardDataType.STRING, new StringField());
    standardDataTypes.put(StandardDataType.INTEGER_WRAPPER, integerField);
    standardDataTypes.put(StandardDataType.INTEGER_PRIMITIVE, integerField);
    standardDataTypes.put(StandardDataType.LONG_WRAPPER, longField);
    standardDataTypes.put(StandardDataType.LONG_PRIMITIVE, longField);
    standardDataTypes.put(StandardDataType.FLOAT_WRAPPER, floatField);
    standardDataTypes.put(StandardDataType.FLOAT_PRIMITIVE, floatField);
    standardDataTypes.put(StandardDataType.DOUBLE_WRAPPER, doubleField);
    standardDataTypes.put(StandardDataType.DOUBLE_PRIMITIVE, doubleField);
    standardDataTypes.put(StandardDataType.BYTE_WRAPPER, byteField);
    standardDataTypes.put(StandardDataType.BYTE_PRIMITIVE, byteField);
    standardDataTypes.put(StandardDataType.BOOLEAN_WRAPPER, booleanField);
    standardDataTypes.put(StandardDataType.BOOLEAN_PRIMITIVE, booleanField);
    this.standardDataTypes = Collections.unmodifiableMap(standardDataTypes);
  }

  public boolean isStandardDataType(String typeName) {
    return StandardDataType.isStandardDataType(typeName);
  }

  public boolean isStandardDataType(Class<?> clazz) {
    return StandardDataType.isStandardDataType(clazz);
  }

  public boolean isPrimitiveDataType(String typeName) {
    return StandardDataType.isPrimitiveDataType(typeName);
  }

  public StandardField getStandardField(String typeName) throws ObjectConverterException {
    StandardDataType standardDataType = getStandardDataType(typeName);
    StandardField standardField = standardDataTypes.get(standardDataType);
    if (standardField == null) {
      throw new ObjectConverterException(
          String.format("Cannot find standard field from type name: %s", typeName));
    }
    return standardField;
  }

  private StandardDataType getStandardDataType(String typeName) throws ObjectConverterException {
    try {
      return StandardDataType.fromTypeName(typeName);
    } catch (StandardDataTypeNotFoundException e) {
      throw new ObjectConverterException(
          String.format("Cannot find standard data type from type name: %s", typeName), e);
    }
  }
}
