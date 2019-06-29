package de.mcella.openapi.v3.objectconverter;

public class ObjectConverterMain {

  public static void main(String... arguments) {
    if (arguments.length == 1) {
      if (arguments[0].equals("--help") || arguments[0].equals("help")) {
        printHelp();
      } else {
        String className = arguments[0];
        convert(className);
      }
      System.exit(0);
    } else {
      System.out.println("Wrong object-converter application usage.");
      printHelp();
      System.exit(1);
    }
  }

  private static void convert(String className) {
    try {
      Converter.convert(className);
    } catch (ObjectConverterException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private static void printHelp() {
    System.out.println("Usage:");
    System.out.println("java -jar object-converter-0.0.1.jar className");
    System.out.println("with className parameter the full class name");
  }
}
