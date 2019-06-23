# Java class conversion to OpenAPI 3 YAML Schema Object

This project builds a Java application used for converting a Java class definition to an OpenAPI 3 YAML document.
The OpenAPI 3 document generated is compliant to YAML version 1.2 format.

The official OpenAPI 3 documentation is:

[OpenAPI 3.0.0 documentation](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md)

## Development

### Setup

Install OpenJDK 11.

### Build project

gradlew clean build

### Run project

cd build/libs

java -jar  build/libs/object-converter-1.0-SNAPSHOT.jar <Java-class-canonical-name>

__Example:__

java -jar build/libs/object-converter-1.0-SNAPSHOT.jar de.mcella.openapi.v3.objectconverter.Example

__Example with external class:__

javac example-external\src\ExampleFromExternal.java -d example-external\classes

java -cp build\libs\*;example-external\classes de.mcella.openapi.v3.objectconverter.ObjectConverterMain  de.mcella.openapi.v3.objectconverter.example.ExampleFromExternal

The OpenAPI 3 document generated is a document named "openapi.yaml".

### OpenAPI 3 document validation

Run the following gradle command to validate the generated document:

gradlew openApiValidate

### Source code style

This project follows the Google Java Style, and uses the [google-java-format](https://github.com/google/google-java-format) formatter.

## Documentation

This application is useful to convert a complex Java class to an OpenAPI 3 Schema Object definition.

The generated OpenAPI 3 document is a single and complete OpenAPI 3 document with a simple template.
The converted Java class is inserted as a Schema Object into a sample POST request with body content "application/json".

Assumptions:
- The Schema Object does not contain Reference Objects
- The Schema Object contains a subset of the available OpenAPI specification properties
- The application does not support composition and inheritance using the "allOf" and "discriminator" properties

### Data Types

The supported OpenAPI 3 data types are the following:

| Common name | type | format | Comments |
| :------: | :------: | :------: | :------: |
| integer | integer | int32 | signed 32 bits |
| long | integer | int64 | signed 64 bits |
| float | number | float | |
| double | number | double | |
| string | string | | |
| byte | string | byte | base64 encoded characters |
| boolean | boolean | | |

The application supports both Java primitive types and Java wrapper classes.

### Features

The application supports the conversion of:
- Java primitive and Java wrapper classes
- Custom classes
- Lists (with a defined Generic Class)
- Dictionaries