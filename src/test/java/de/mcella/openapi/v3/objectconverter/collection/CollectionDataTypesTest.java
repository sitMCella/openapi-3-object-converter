package de.mcella.openapi.v3.objectconverter.collection;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

public class CollectionDataTypesTest {

  private CollectionDataTypes collectionDataTypes;

  @Before
  public void setUp() {
    this.collectionDataTypes = new CollectionDataTypes();
  }

  @Test
  public void shouldGetCollectionFieldFromListTypeName() throws ObjectConverterException {
    CollectionField collectionField = collectionDataTypes.getCollectionField("java.util.List");
    assertTrue(collectionField instanceof ListField);
  }

  @Test
  public void shouldGetCollectionFieldFromMapTypeName() throws ObjectConverterException {
    CollectionField collectionField = collectionDataTypes.getCollectionField("java.util.Map");
    assertTrue(collectionField instanceof MapField);
  }

  @Test(expected = ObjectConverterException.class)
  public void shouldThrowObjectConverterExceptionOnGetCollectionFieldFromUnknownTypeName()
      throws ObjectConverterException {
    collectionDataTypes.getCollectionField("unknown.data.type");
  }
}
