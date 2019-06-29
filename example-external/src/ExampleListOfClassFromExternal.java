package de.mcella.openapi.v3.objectconverter.example;

import java.util.List;

public class ExampleListOfClassFromExternal {
  public final List<ExampleFromExternal> exampleFromExternal;

  public ExampleListOfClassFromExternal(List<ExampleFromExternal> exampleFromExternal) {
    this.exampleFromExternal = exampleFromExternal;
  }
}
