package br.com.engdb.services.devportal.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationResource.ServiceDescriptionDocumentationStatus;
import br.com.engdb.services.devportal.service.documents.ServiceDescriptionDocumentation;

@DataMongoTest
@RunWith(SpringRunner.class)
public class ServiceDescriptionDocumentationRepositoryTest {
	
	@Autowired
	private ServiceDescriptionDocumentationRepository repository;
	
	@Before
	public void setup() throws Exception {
		

		// given
		ServiceDescriptionDocumentation serviceDocumentation1 = new ServiceDescriptionDocumentation();
		serviceDocumentation1.setId("1");
		serviceDocumentation1.setServiceDescriptionId("1");
		serviceDocumentation1.setStatus(ServiceDescriptionDocumentationStatus.CURRENT);
		
		ServiceDescriptionDocumentation serviceDocumentation2 = new ServiceDescriptionDocumentation();
		serviceDocumentation2.setId("2");
		serviceDocumentation2.setServiceDescriptionId("1");
		serviceDocumentation2.setStatus(ServiceDescriptionDocumentationStatus.INACTIVE);
       
        // when
		repository.save(serviceDocumentation1);
		repository.save(serviceDocumentation2);

    }
	 
    @Test
    public void findByServiceDescIdAndStatusTest() {

    	Optional<ServiceDescriptionDocumentation> result = repository.findByServiceDescIdAndStatus("1", ServiceDescriptionDocumentationStatus.CURRENT.toString());
    	
        assertEquals("1", result.get().getId());
 
    }
    
    @Test
    public void findByServiceDescIdAndDocIdTest() {

    	Optional<ServiceDescriptionDocumentation> result = repository.findByServiceDescIdAndDocId("1", "2");
    	
        assertEquals("2", result.get().getId());
 
    }
    
    @Test
    public void findAllByServiceDescriptionIdTest() {

    	Stream<ServiceDescriptionDocumentation> result = repository.findAllByServiceDescriptionId("1").get();
    	
        assertEquals(2, result.count());
 
    }
    
    

}