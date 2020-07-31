package br.com.engdb.services.devportal.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.engdb.services.devportal.resources.ServiceDescriptionResource.ServiceDescriptionStatus;
import br.com.engdb.services.devportal.service.documents.ServiceDescription;

@DataMongoTest
@RunWith(SpringRunner.class)
public class ServiceDescriptionRepositoryTest {
	
	@Autowired
	private ServiceDescriptionRepository repository;
	
	@Before
	public void setup() throws Exception {
		
		/*
		List<Category> categories1 = new ArrayList<>();
		categories1.add(new Category());
		
		List<Category> categories2 = new ArrayList<>();
		categories2.add(new Category((long) 1, "Category1"));
		categories2.add(new Category((long) 1, "Category2"));
		*/

		// given
		ServiceDescription serviceDescription1 = new ServiceDescription();
		serviceDescription1.setId("1");
		serviceDescription1.setName("Teste1");
		serviceDescription1.setDescription("Teste1 Description");
		//serviceDescription1.setCategory(categories1);
		serviceDescription1.setStatus(ServiceDescriptionStatus.PUBLISHED);
		
		ServiceDescription serviceDescription2 = new ServiceDescription();
		serviceDescription2.setId("2");
		serviceDescription2.setName("Teste2");
		serviceDescription2.setDescription("Teste2 Description");
		//serviceDescription2.setCategory(categories2);
		serviceDescription2.setStatus(ServiceDescriptionStatus.DRAFT);
       
        // when
		repository.save(serviceDescription1);
		repository.save(serviceDescription2);

    }
	 
    @Test
    public void findByCategoriesNameTest() {
		Pageable pageRequest = PageRequest.of(0, 10, Direction.ASC, "id");

        assertEquals(0, repository.findByCategoriesName("Category2", pageRequest).get().count());
 
    }
    
    @Test
    public void findBySearchWordTest() {
		Pageable pageRequest = PageRequest.of(0, 10, Direction.ASC, "id");

        assertEquals(1, repository.findBySearchWord("Teste1", pageRequest).get().get().count());
 
    }

    @Test
    public void findByCategoriesAndSearchWordTest() {
		Pageable pageRequest = PageRequest.of(0, 10, Direction.ASC, "id");

        assertEquals(0, repository.findByCategoriesAndSearchWord("Category1","Teste1" ,pageRequest).get().count());
 
    }
}