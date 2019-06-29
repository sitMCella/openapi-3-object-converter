package de.mcella.openapi.v3.objectconverter.datatype;

public enum StandardDataType {
  STRING("java.lang.String", false),
  INTEGER_WRAPPER("java.lang.Integer", false),
  INTEGER_PRIMITIVE("int", true),
  LONG_WRAPPER("java.lang.Long", false),
  LONG_PRIMITIVE("long", true),
  FLOAT_WRAPPER("java.lang.Float", false),
  FLOAT_PRIMITIVE("float", true),
  DOUBLE_WRAPPER("java.lang.Double", false),
  DOUBLE_PRIMITIVE("double", true),
  BYTE_WRAPPER("java.lang.Byte", false),
  BYTE_PRIMITIVE("byte", true),
  BOOLEAN_WRAPPER("java.lang.Boolean", false),
  BOOLEAN_PRIMITIVE("boolean", true);

  private final String typeName;
  private final boolean primitive;

  StandardDataType(String typeName, boolean primitive) {
    this.typeName = typeName;
    this.primitive = primitive;
  }

  public String getTypeName() {
    return typeName;
  }

  public boolean isPrimitive() {
    return primitive;
  }

  public static StandardDataType fromTypeName(String typeName)
      throws StandardDataTypeNotFoundException {
    for (StandardDataType standardDataType : values()) {
      if (standardDataType.getTypeName().equals(typeName)) {
        return standardDataType;
      }
    }
    throw new StandardDataTypeNotFoundException(typeName);
  }

  public static boolean isStandardDataType(String typeName) {
    for (StandardDataType standardDataType : values()) {
      if (standardDataType.getTypeName().equals(typeName)) {
        return true;
      }
    }
    return false;
  }
}
