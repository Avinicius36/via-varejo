package br.com.engdb.services.devportal.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.engdb.services.devportal.repositories.CategoryRepository;
import br.com.engdb.services.devportal.repositories.ServiceDescriptionRepository;
import br.com.engdb.services.devportal.resources.ServiceDescriptionPutPostResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionResource;
import br.com.engdb.services.devportal.service.documents.Category;
import br.com.engdb.services.devportal.service.documents.ServiceDescription;
import br.com.engdb.services.devportal.service.mappers.ServiceDescriptionToResourceMapper;

/**
 * Junit para testes unitário do serviço {@link ServiceDescriptionService}
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceDescriptionServiceTest {

	@InjectMocks
	private ServiceDescriptionService service;

	@Mock
	private ServiceDescriptionToResourceMapper mapper;
	
	@Mock
	private ServiceDescriptionRepository repository;
	
	@Mock
	private CategoryRepository categoryRepository;

	@Before
	public void init() {
		// Initialize objects annotated with @Mock, @Captor and @Spy.
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getByIdTest() throws Exception {

		ServiceDescription documentToReturn = new ServiceDescription();
		documentToReturn.setId("1");
		documentToReturn.setName("Teste");

		ServiceDescriptionResource resourceToReturn = new ServiceDescriptionResource();
		resourceToReturn.setId("1");
		resourceToReturn.setName("Teste");
		
		Optional<ServiceDescription> optionalToReturn = Optional.of(documentToReturn);

		Mockito.when(repository.findById("1")).thenReturn(optionalToReturn);
		Mockito.when(mapper.apply(documentToReturn)).thenReturn(resourceToReturn);

		ServiceDescriptionResource returnedResource = service.getById("1").get();

		verify(repository, times(1)).findById("1");

		assertEquals(resourceToReturn.getId(), returnedResource.getId());
		assertEquals(resourceToReturn.getName(), returnedResource.getName());
		assertNull(returnedResource.getDescription());
		assertNull(returnedResource.getVersion());
		assertNull(returnedResource.getStatus());
		assertNull(returnedResource.getCategories());
		assertNull(returnedResource.getOwner());
		assertNull(returnedResource.getKeywords());
		assertNull(returnedResource.getTags());
		assertNull(returnedResource.getDocumentationSource());
		assertNull(returnedResource.getLastUpdate());

	}
	/*
	 * @Test public void getAllTest() throws Exception {
	 * 
	 * ServiceDescription serviceDescription = new ServiceDescription();
	 * serviceDescription.setId("1"); serviceDescription.setName("Teste");
	 * 
	 * List<ServiceDescription> serviceDescriptionList = new
	 * ArrayList<ServiceDescription>(); Page<ServiceDescription> pageToReturn = new
	 * PageImpl<>(serviceDescriptionList);
	 * 
	 * Pageable pageRequest = PageRequest.of(0, 10, Direction.ASC, "id");
	 * 
	 * Mockito.when(repository.findAll(pageRequest)).thenReturn(pageToReturn);
	 * 
	 * 
	 * Optional<String> category = null; Optional<String> search = null;
	 * 
	 * Page<ServiceDescriptionResource> returnedResource =
	 * service.getAll(pageRequest, category, search);
	 * 
	 * verify(repository, times(1)).findAll(pageRequest);
	 * 
	 * assertEquals(serviceDescription.getId(),
	 * returnedResource.getContent().get(0).getId());
	 * assertEquals(serviceDescription.getName(),
	 * returnedResource.getContent().get(0).getName());
	 * assertNull(returnedResource.getContent().get(0).getDescription());
	 * assertNull(returnedResource.getContent().get(0).getVersion());
	 * assertNull(returnedResource.getContent().get(0).getStatus());
	 * assertEquals(serviceDescription.getCategories(),
	 * returnedResource.getContent().get(0).getCategories());
	 * assertNull(returnedResource.getContent().get(0).getOwner());
	 * assertNull(returnedResource.getContent().get(0).getKeywords());
	 * assertNull(returnedResource.getContent().get(0).getTags());
	 * assertNull(returnedResource.getContent().get(0).getDocumentationSource());
	 * assertNull(returnedResource.getContent().get(0).getLastUpdate());
	 * 
	 * }
	 * 
	 * @Test public void createTest() throws Exception {
	 * 
	 * ServiceDescriptionPutPostResource resourceToReturn = new
	 * ServiceDescriptionPutPostResource(); resourceToReturn.setName("Teste");
	 * 
	 * ServiceDescription documentToReturn = new ServiceDescription();
	 * documentToReturn.setId("1"); documentToReturn.setName("Teste");
	 * 
	 * service.create(resourceToReturn);
	 * 
	 * Mockito.when(repository.save(documentToReturn)).thenReturn(null);
	 * Mockito.when(categoryRepository.findById("1").thenReturn(Optional.of(new
	 * Category())); // Captura o que foi passado para o save do parameterRepository
	 * e checka se foi // chamado apenas uma vez final
	 * ArgumentCaptor<ServiceDescription> captor =
	 * ArgumentCaptor.forClass(ServiceDescription.class); Mockito.verify(repository,
	 * times(1)).save(captor.capture());
	 * 
	 * ServiceDescription createdServiceDescription = captor.getAllValues().get(0);
	 * 
	 * assertEquals(resourceToReturn.getName(),
	 * createdServiceDescription.getName());
	 * assertNull(createdServiceDescription.getDescription());
	 * assertNull(createdServiceDescription.getVersion());
	 * assertNull(createdServiceDescription.getStatus());
	 * assertNull(createdServiceDescription.getCategories());
	 * assertNull(createdServiceDescription.getOwner());
	 * assertNull(createdServiceDescription.getKeywords());
	 * assertNull(createdServiceDescription.getTags());
	 * assertNull(createdServiceDescription.getDocumentationSource());
	 * assertNull(createdServiceDescription.getLastUpdate());
	 * 
	 * }
	 * 
	 * @Test public void deleteTest() throws Exception {
	 * 
	 * ServiceDescriptionResource resourceToReturn = new
	 * ServiceDescriptionResource(); resourceToReturn.setId(("1"));
	 * resourceToReturn.setName("Teste");
	 * 
	 * ServiceDescription documentToReturn = new ServiceDescription();
	 * documentToReturn.setId("1"); documentToReturn.setName("Teste");
	 * 
	 * service.delete(resourceToReturn.getId());
	 * 
	 * // Captura o que foi passado para o save do parameterRepository e checka se
	 * foi // chamado apenas uma vez final ArgumentCaptor<String> captor =
	 * ArgumentCaptor.forClass(String.class); Mockito.verify(repository,
	 * times(1)).deleteById(captor.capture());
	 * 
	 * String serviceDescriptionId = captor.getAllValues().get(0);
	 * 
	 * assertEquals(resourceToReturn.getId(), serviceDescriptionId); }
	 */

}