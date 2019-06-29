package de.mcella.openapi.v3.objectconverter.collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import de.mcella.openapi.v3.objectconverter.ConverterService;
import de.mcella.openapi.v3.objectconverter.ObjectConverterException;

public class CollectionDataTypesTest {

  private final ConverterService converterService = mock(ConverterService.class);

  private CollectionDataTypes collectionDataTypes;

  @Before
  public void setUp() {
    this.collectionDataTypes = new CollectionDataTypes(converterService);
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
