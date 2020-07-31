package br.com.engdb.services.devportal.controller.integratedtests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.engdb.services.devportal.controller.ServiceDescriptionDocumentationController;
import br.com.engdb.services.devportal.service.ServiceDescriptionDocumentationService;

/**
 * Junit para testes integrados da API para ServiceDescriptionDocumentationController <BR><BR>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ServiceDescriptionDocumentationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "test")
public class ServiceDescriptionDocumentationControllerTest {

	@MockBean
	private ServiceDescriptionDocumentationService service;

	@Test
	@WithMockUser(username = "test", roles = "READ")
	public void getServiceByIdTest() throws Exception {
		
		/*
		ServiceDescriptionDocumentationResource serviceDescription = new ServiceDescriptionDocumentationResource();
		serviceDescription.setContent("Teste");
		
		given(service.getByServiceDescriptionIdCurrent((long) 1)).willReturn(Optional.of(serviceDescription));

		mvc.perform(get("/api/v1/services-descriptions/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content", is(serviceDescription.getContent())));
		 */
	}
	
	@Test
	@WithMockUser(username = "test", roles = "WHATEVER")
	public void getServiceDescriptionForbidden() throws Exception {
		/*
		given(service.getById((long) 1)).willReturn(Optional.of(new ServiceDescriptionDocumentationResource()));

		mvc.perform(get("/api/v1/services-descriptions/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
		*/
	}
	
	@Test
	@WithMockUser(username = "test", roles = "READ")
	public void getServiceDescriptionNotFound() throws Exception {
		/*
		given(service.getById((long) 1)).willReturn(Optional.empty());

		mvc.perform(get("/api/v1/services-descriptions/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
		 */
	}

}