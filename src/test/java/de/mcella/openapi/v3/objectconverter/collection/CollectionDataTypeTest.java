package de.mcella.openapi.v3.objectconverter.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class CollectionDataTypeTest {

  @Test
  public void shouldGetListTypeName() {
    String typeName = CollectionDataType.LIST.getTypeName();
    assertThat(typeName, equalTo("java.util.List"));
  }

  @Test
  public void shouldGetMapTypeName() {
    String typeName = CollectionDataType.MAP.getTypeName();
    assertThat(typeName, equalTo("java.util.Map"));
  }

  @Test
  public void shouldGetListCollectionDataTypeFromTypeName()
      throws CollectionDataTypeNotFoundException {
    CollectionDataType collectionDataType = CollectionDataType.fromTypeName("java.util.List");
    assertThat(collectionDataType, equalTo(CollectionDataType.LIST));
  }

  @Test
  public void shouldGetMapCollectionDataTypeFromTypeName()
      throws CollectionDataTypeNotFoundException {
    CollectionDataType collectionDataType = CollectionDataType.fromTypeName("java.util.Map");
    assertThat(collectionDataType, equalTo(CollectionDataType.MAP));
  }

  @Test(expected = CollectionDataTypeNotFoundException.class)
  public void
      shouldThrowCollectionDataTypeNotFoundExceptionOnGetCollectionDataTypeFromUnknownTypeName()
          throws CollectionDataTypeNotFoundException {
    CollectionDataType.fromTypeName("unknown.data.type");
  }

  @Test
  public void shouldListTypeNameBeACollectionDataType() {
    assertTrue(CollectionDataType.isCollectionDataType("java.util.List"));
  }

  @Test
  public void shouldMapTypeNameBeACollectionDataType() {
    assertTrue(CollectionDataType.isCollectionDataType("java.util.Map"));
  }

  @Test
  public void shouldUnknownTypeNameNotBeACollectionDataType() {
    assertFalse(CollectionDataType.isCollectionDataType("unknown.data.type"));
  }
}
