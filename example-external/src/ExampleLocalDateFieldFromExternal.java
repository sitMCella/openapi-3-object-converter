package de.mcella.openapi.v3.objectconverter.example;

import java.time.LocalDate;

public class ExampleLocalDateFieldFromExternal {
  public final LocalDate localDateField;

  public ExampleLocalDateFieldFromExternal(LocalDate localDateField) {
    this.localDateField = localDateField;
  }
}
