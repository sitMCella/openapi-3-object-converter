package de.mcella.openapi.v3.objectconverter;

import org.junit.Test;

import de.mcella.openapi.v3.objectconverter.Converter;
import de.mcella.openapi.v3.objectconverter.ObjectConverterException;
import de.mcella.openapi.v3.objectconverter.ObjectConverterMain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.io.File;

import static de.mcella.openapi.v3.objectconverter.Converter.OPENAPI_FILE_NAME;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.hasItem;

public class ConverterTest {

  private static final String INFO_YAML_SECTION_TITLE = "info:";
  private static final String OPENAPI_YAML_SECTION_TITLE = "openapi: 3.0.0";
  private static final String SERVERS_YAML_SECTION_TITLE = "servers:";
  private static final String PATHS_YAML_SECTION_TITLE = "paths:";
  private static final List<String> OPENAPI_YAML_STOP_LINES =
      List.of(
          INFO_YAML_SECTION_TITLE,
          OPENAPI_YAML_SECTION_TITLE,
          SERVERS_YAML_SECTION_TITLE,
          PATHS_YAML_SECTION_TITLE);
  private static final List<String> PATH_SECTION_FIRST_PART =
      List.of(
          PATHS_YAML_SECTION_TITLE,
          "  /sample:",
          "    post: ",
          "      description: Generated sample post request from template class",
          "      requestBody:",
          "        content:",
          "          application/json:");
  private static final List<String> PATH_SECTION_SECOND_PART =
      List.of(
          "        description: Generated content body",
          "      responses:",
          "        '200':",
          "          description: Generated sample post response from template class",
          "      summary: Sample post request");

  @Test
  public void shouldCreateYamlFileDuringClassConvert()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.PublicStringField";

    Converter.convert(className);

    getConverterOutputFile();
  }

  @Test
  public void shouldCreateYamlTemplateSectionsDuringConvertion()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.PublicStringField";

    Converter.convert(className);

    List<String> expectedInfoSection =
        List.of(
            INFO_YAML_SECTION_TITLE,
            "  description: Generated API example from template class",
            "  title: API example",
            "  version: 1.0.0");
    List<String> expectedOpenApiVersionSection = List.of(OPENAPI_YAML_SECTION_TITLE);
    List<String> expectedServersSection =
        List.of(SERVERS_YAML_SECTION_TITLE, "- url: http://localhost:8080");
    verifyTemplateSections(
        expectedInfoSection, expectedOpenApiVersionSection, expectedServersSection);
  }

  @Test
  public void shouldConvertStringClass() throws ObjectConverterException, IOException {
    String className = "java.lang.String";

    Converter.convert(className);

    List<String> expectedSchema = List.of("            schema:", "              type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertIntegerClass() throws ObjectConverterException, IOException {
    String className = "java.lang.Integer";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:", "              type: integer", "              format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertIntegerPrimitive() throws ObjectConverterException, IOException {
    String className = "int";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:", "              type: integer", "              format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertLongClass() throws ObjectConverterException, IOException {
    String className = "java.lang.Long";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:", "              type: integer", "              format: int64");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertLongPrimitive() throws ObjectConverterException, IOException {
    String className = "long";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:", "              type: integer", "              format: int64");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertFloatClass() throws ObjectConverterException, IOException {
    String className = "java.lang.Float";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of("            schema:", "              type: number", "              format: float");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertFloatPrimitive() throws ObjectConverterException, IOException {
    String className = "float";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of("            schema:", "              type: number", "              format: float");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertDoubleClass() throws ObjectConverterException, IOException {
    String className = "java.lang.Double";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:", "              type: number", "              format: double");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertDoublePrimitive() throws ObjectConverterException, IOException {
    String className = "double";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:", "              type: number", "              format: double");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertByteClass() throws ObjectConverterException, IOException {
    String className = "java.lang.Byte";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of("            schema:", "              type: string", "              format: byte");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertBytePrimitive() throws ObjectConverterException, IOException {
    String className = "byte";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of("            schema:", "              type: string", "              format: byte");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertBooleanClass() throws ObjectConverterException, IOException {
    String className = "java.lang.Boolean";

    Converter.convert(className);

    List<String> expectedSchema = List.of("            schema:", "              type: boolean");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertBooleanPrimitive() throws ObjectConverterException, IOException {
    String className = "boolean";

    Converter.convert(className);

    List<String> expectedSchema = List.of("            schema:", "              type: boolean");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertLocalDate() throws ObjectConverterException, IOException {
    String className = "java.time.LocalDate";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of("            schema:", "              type: string", "              format: date");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertOffsetDateTime() throws ObjectConverterException, IOException {
    String className = "java.time.OffsetDateTime";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:", "              type: string", "              format: date-time");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneStringField() throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.PublicStringField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                field:",
            "                  type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneIntegerWrapperField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.IntegerWrapperField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                field:",
            "                  type: integer",
            "                  format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneIntegerPrimitiveField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.IntegerPrimitiveField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                field:",
            "                  type: integer",
            "                  format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneLongWrapperField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.LongWrapperField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                field:",
            "                  type: integer",
            "                  format: int64");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneLongPrimitiveField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.LongPrimitiveField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                field:",
            "                  type: integer",
            "                  format: int64");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOnePrivateStringField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.PrivateStringField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                field:",
            "                  type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOnePublicLocalDateField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.LocalDateField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                field:",
            "                  type: string",
            "                  format: date");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOnePublicOffsetDateTimeField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.OffsetDateTimeField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                field:",
            "                  type: string",
            "                  format: date-time");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneListOfStringsField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.ListOfStringsField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: array",
            "                  items:",
            "                    type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneListOfIntegersField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.ListOfIntegerWrappersField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: array",
            "                  items:",
            "                    type: integer",
            "                    format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneListOfListsOfStringField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.ListOfListOfStringsField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: array",
            "                  items:",
            "                    type: array",
            "                    items:",
            "                      type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneListOfListsOfListsOfStringField()
      throws ObjectConverterException, IOException {
    String className =
        "de.mcella.openapi.v3.objectconverter.example.ListOfListOfListOfStringsField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: array",
            "                  items:",
            "                    type: array",
            "                    items:",
            "                      type: array",
            "                      items:",
            "                        type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneListOfIntegerWrapperClassField()
      throws ObjectConverterException, IOException {
    String className =
        "de.mcella.openapi.v3.objectconverter.example.ListOfIntegerWrapperClassField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: array",
            "                  items:",
            "                    type: object",
            "                    properties:",
            "                      field:",
            "                        type: integer",
            "                        format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneMapOfStringToStringField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.MapOfStringToStringField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: object",
            "                  additionalProperties:",
            "                    type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneMapOfStringToIntegerField()
      throws ObjectConverterException, IOException {
    String className = "de.mcella.openapi.v3.objectconverter.example.MapOfStringToIntegerField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: object",
            "                  additionalProperties:",
            "                    type: integer",
            "                    format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneMapOfStringToIntegerWrapperClassField()
      throws ObjectConverterException, IOException {
    String className =
        "de.mcella.openapi.v3.objectconverter.example.MapOfStringToIntegerWrapperClassField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: object",
            "                  additionalProperties:",
            "                    type: object",
            "                    properties:",
            "                      field:",
            "                        type: integer",
            "                        format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneListOfMapOfStringToStringField()
      throws ObjectConverterException, IOException {
    String className =
        "de.mcella.openapi.v3.objectconverter.example.ListOfMapOfStringToStringField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: array",
            "                  items:",
            "                    type: object",
            "                    additionalProperties:",
            "                      type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneListOfMapOfStringToIntegerWrapperClassField()
      throws ObjectConverterException, IOException {
    String className =
        "de.mcella.openapi.v3.objectconverter.example.ListOfMapOfStringToIntegerWrapperClassField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: array",
            "                  items:",
            "                    type: object",
            "                    additionalProperties:",
            "                      type: object",
            "                      properties:",
            "                        field:",
            "                          type: integer",
            "                          format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertClassWithOneMapOfStringToListOfIntegerWrapperClassField()
      throws ObjectConverterException, IOException {
    String className =
        "de.mcella.openapi.v3.objectconverter.example.MapOfStringToListOfIntegerWrapperClassField";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              properties:",
            "                fields:",
            "                  type: object",
            "                  additionalProperties:",
            "                    type: array",
            "                    items:",
            "                      type: object",
            "                      properties:",
            "                        field:",
            "                          type: integer",
            "                          format: int32");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertListOfString() throws ObjectConverterException, IOException {
    String className = "java.util.List<java.lang.String>";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: array",
            "              items:",
            "                type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertListOfPublicStringFieldClass()
      throws ObjectConverterException, IOException {
    String className =
        "java.util.List<de.mcella.openapi.v3.objectconverter.example.PublicStringField>";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: array",
            "              items:",
            "                type: object",
            "                properties:",
            "                  field:",
            "                    type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertMapOfStringToString() throws ObjectConverterException, IOException {
    String className = "java.util.Map<java.lang.String, java.lang.String>";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              additionalProperties:",
            "                type: string");
    verifyPathsSection(expectedSchema);
  }

  @Test
  public void shouldConvertMapOfStringToPublicStringFieldClass()
      throws ObjectConverterException, IOException {
    String className =
        "java.util.Map<java.lang.String, de.mcella.openapi.v3.objectconverter.example.PublicStringField>";

    Converter.convert(className);

    List<String> expectedSchema =
        List.of(
            "            schema:",
            "              type: object",
            "              additionalProperties:",
            "                type: object",
            "                properties:",
            "                  field:",
            "                    type: string");
    verifyPathsSection(expectedSchema);
  }

  private void verifyTemplateSections(
      List<String> expectedInfoSection,
      List<String> expectedOpenApiVersionSection,
      List<String> expectedServersSection)
      throws IOException {
    Path openApiFile = getConverterOutputFile();
    compareText(openApiFile, expectedInfoSection, INFO_YAML_SECTION_TITLE, false);
    compareText(openApiFile, expectedOpenApiVersionSection, OPENAPI_YAML_SECTION_TITLE, false);
    compareText(openApiFile, expectedServersSection, SERVERS_YAML_SECTION_TITLE, false);
  }

  private void verifyPathsSection(List<String> expectedSchema) throws IOException {
    List<String> expected = new ArrayList<>();
    expected.addAll(PATH_SECTION_FIRST_PART);
    expected.addAll(expectedSchema);
    expected.addAll(PATH_SECTION_SECOND_PART);
    Path openApiFile = getConverterOutputFile();
    compareText(openApiFile, expected, PATHS_YAML_SECTION_TITLE, true);
  }

  private void compareText(
      Path openApiFile, List<String> expectedTextLines, String startLine, boolean order)
      throws IOException {
    AtomicInteger counter = new AtomicInteger(0);
    AtomicBoolean doCompare = new AtomicBoolean(false);
    List<String> stopLines = new ArrayList<>();
    stopLines.addAll(OPENAPI_YAML_STOP_LINES);
    stopLines.remove(startLine);
    try (Stream<String> stream = Files.lines(openApiFile)) {
      stream.forEach(
          line -> {
            System.out.println(line);
            if (line.equals(startLine)) {
              doCompare.set(true);
            } else if (stopLines.contains(line)) {
              doCompare.set(false);
            }
            if (doCompare.get()) {
              int counterValue = counter.getAndIncrement();
              if (order) {
                assertEquals(expectedTextLines.get(counterValue), line);
              } else {
                assertThat(expectedTextLines, hasItem(line));
              }
            }
          });
    }
    assertEquals(expectedTextLines.size(), counter.get());
  }

  private Path getConverterOutputFile() throws IOException {
    File jarFile =
        new File(
            ObjectConverterMain.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath());
    Path jarFolder = Paths.get(jarFile.toURI()).getParent();
    try (Stream<Path> filePathStream = Files.walk(jarFolder)) {
      return filePathStream
          .filter(filePath -> Files.isRegularFile(filePath) && filePath.endsWith(OPENAPI_FILE_NAME))
          .findFirst()
          .orElseThrow(() -> new FileNotFoundException(OPENAPI_FILE_NAME));
    }
  }
}
