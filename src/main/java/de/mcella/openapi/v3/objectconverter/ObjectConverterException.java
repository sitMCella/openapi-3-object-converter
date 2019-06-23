package de.mcella.openapi.v3.objectconverter;

public class ObjectConverterException extends Exception {

  private static final long serialVersionUID = -8118225603465770546L;

  public ObjectConverterException(String message) {
    super(message);
  }

  public ObjectConverterException(String message, Throwable cause) {
    super(message, cause);
  }
}
