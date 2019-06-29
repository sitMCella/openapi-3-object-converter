package de.mcella.openapi.v3.objectconverter;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class Converter {

  public static final String OUTPUT_FILE_NAME = "output.yaml";
  public static final String OPENAPI_FILE_NAME = "openapi.yaml";

  public static void convert(String className) throws ObjectConverterException {
    File jarFile =
        new File(
            ObjectConverterMain.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath());
    Path jarFolder = Paths.get(jarFile.toURI()).getParent();
    Path outputFile = jarFolder.resolve(OUTPUT_FILE_NAME);
    try {
      DumperOptions options = new DumperOptions();
      options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
      options.setPrettyFlow(true);
      Yaml yaml = new Yaml(options);
      FileWriter writer = new FileWriter(outputFile.toFile());

      Map<String, Object> schemaDefinition = ConverterFactory.getInstance().convert(className);

      Template template = new Template();
      template.addRequestBodyContent(new Schema(schemaDefinition));
      yaml.dump(template, writer);

      removeGlobalTags(jarFolder);
    } catch (IOException e) {
      throw new ObjectConverterException(
          String.format("Cannot create output file %s", outputFile.toAbsolutePath()), e);
    }
  }

  private static void removeGlobalTags(Path jarFolder) throws ObjectConverterException {
    Path outputFile = jarFolder.resolve(OUTPUT_FILE_NAME);
    Path openApiFile = jarFolder.resolve(OPENAPI_FILE_NAME);
    try (Stream<String> stream = Files.lines(outputFile);
        BufferedWriter writer = Files.newBufferedWriter(openApiFile, Charset.forName("UTF-8"))) {
      stream.forEach(line -> writeToFile(writer, line));
    } catch (IOException e) {
      throw new ObjectConverterException(
          String.format("Cannot create openapi file %s", openApiFile.toAbsolutePath()), e);
    }
  }

  private static void writeToFile(BufferedWriter writer, String line) {
    try {
      String outputLine = line.replaceAll("!!(\\w*\\.\\w*)*(\\$\\w*)?", "");
      if (!outputLine.isEmpty()) {
        writer.write(outputLine);
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println("Cannot write converted line to openapi file");
      System.out.println(e.getMessage());
    }
  }
}
