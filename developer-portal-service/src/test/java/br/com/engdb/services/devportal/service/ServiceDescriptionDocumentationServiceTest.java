package br.com.engdb.services.devportal.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.devportal.repositories.ServiceDescriptionDocumentationRepository;
import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationResource.ServiceDescriptionDocumentationStatus;
import br.com.engdb.services.devportal.service.documents.ServiceDescriptionDocumentation;
import br.com.engdb.services.devportal.service.mappers.ServiceDescriptionDocumentationDocumentToResourceMapper;

/**
 * Junit para testes unitário do serviço {@link ServiceDescriptionDoumentationService}
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceDescriptionDocumentationServiceTest {

	@Mock
	private ServiceDescriptionDocumentationRepository repository;

	@Mock
	private ServiceDescriptionDocumentationDocumentToResourceMapper mapper;

	@Mock
	private ServiceDescriptionDocumentation document;

	@InjectMocks
	private ServiceDescriptionDocumentationService service;

	@Before
	public void setup() {
		document.setId("1");
		document.setServiceDescriptionId("1");
		document.setContent(new Binary(BsonBinarySubType.BINARY, "Teste".getBytes()));
		document.setCreationDate(LocalDate.now());
		document.setStatus(ServiceDescriptionDocumentationStatus.CURRENT);
		
		//Initialize objects annotated with @Mock, @Captor and @Spy.
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getContentByServiceDescriptionIdCurrentTest() throws Exception {

		Optional<ServiceDescriptionDocumentation> optionalToReturn = Optional.of(document);

		Mockito.when(repository.findByServiceDescIdAndStatus("1", ServiceDescriptionDocumentationStatus.CURRENT.toString())).thenReturn(optionalToReturn);

		Optional<Binary> returnedResource  = service.getContentByServiceDescriptionIdCurrent("1");
		
		verify(repository, times(1)).findByServiceDescIdAndStatus("1", ServiceDescriptionDocumentationStatus.CURRENT.toString());
		
		assertNull(returnedResource.orElse(null));

	}

	@Test
	public void getByServiceDescIdAndDocIdTest() throws Exception {

		Optional<ServiceDescriptionDocumentation> optionalToReturn = Optional.of(document);

		Mockito.when(repository.findByServiceDescIdAndDocId("1", "1")).thenReturn(optionalToReturn);
		Mockito.when(mapper.apply(document)).thenReturn(new ServiceDescriptionDocumentationResource());

		ServiceDescriptionDocumentationResource returnedResource  = service.getByServiceDescIdAndDocId("1", "1").get();

		verify(repository, times(1)).findByServiceDescIdAndDocId("1", "1");

		assertEquals(document.getId(), returnedResource.getId());
		assertEquals(document.getServiceDescriptionId(), returnedResource.getServiceDescriptionId());
		assertEquals(document.getCreationDate(),returnedResource.getCreationDate());
		assertEquals(document.getStatus(), returnedResource.getStatus());

	}

	@Test
	public void getAllByServiceDescIdTest() throws Exception {

		Optional<Stream<ServiceDescriptionDocumentation>> streamToReturn = Optional.of(Stream.of(document));

		Mockito.when(repository.findAllByServiceDescriptionId("1")).thenReturn(streamToReturn);
		Mockito.when(mapper.apply(document)).thenReturn(new ServiceDescriptionDocumentationResource());
		
		List<ServiceDescriptionDocumentationResource> returnedList  = service.getAllByServiceDescId("1");

		verify(repository, times(1)).findAllByServiceDescriptionId("1");

		assertEquals(document.getId(), returnedList.get(0).getId());
		assertEquals(document.getServiceDescriptionId(), returnedList.get(0).getServiceDescriptionId());
		assertEquals(document.getCreationDate(), returnedList.get(0).getCreationDate());
		assertEquals(document.getStatus(), returnedList.get(0).getStatus());

	}

	/*
	@Test
	public void createServiceDescDocumentationTest() throws Exception {

		Mockito.when(repository.findById(document.getServiceDescriptionId())).thenReturn(Optional.of(document));
		Mockito.when(repository.findAllByServiceDescriptionId(document.getServiceDescriptionId())).thenReturn(Optional.of(Stream.of(document)));
		Mockito.when(repository.save(document)).thenReturn(document);
		Mockito.when(mapper.apply(document)).thenReturn(new ServiceDescriptionDocumentationResource());

		service.createServiceDescDocumentation("{teste:\"teste\"}".getBytes(), "1");

		// Captura o que foi passado para o save do parameterRepository e checka se foi chamado apenas uma vez
		final ArgumentCaptor<ServiceDescriptionDocumentation> captor = ArgumentCaptor.forClass(ServiceDescriptionDocumentation.class);
		Mockito.verify(repository, times(1)).save(captor.capture());

		ServiceDescriptionDocumentation s = captor.getAllValues().get(0);
		assertEquals("1", s.getServiceDescriptionId());
		assertEquals("{teste:\"teste\"}".getBytes(), s.getContent().getData());

	}*/


	/*
	@Test
	public void updateServiceDescDocumentationTest() throws Exception {

		Optional<ServiceDescriptionDocumentation> optional = Optional.of(document);

		Mockito.when(repository.findByServiceDescIdAndDocId("1", "1")).thenReturn(optional);
		Mockito.when(mapper.apply(document)).thenReturn(new ServiceDescriptionDocumentationResource());

		service.updateServiceDescDocumentation("{status:\"current\"}", "1", "1");

		// Captura o que foi passado para o save do parameterRepository e checka se foi chamado apenas uma vez
		final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(repository, times(1)).findByServiceDescIdAndDocId("1", "1");

		assertEquals("1", captor.getAllValues().get(0));
		assertEquals("1", captor.getAllValues().get(1));
	}*/

}