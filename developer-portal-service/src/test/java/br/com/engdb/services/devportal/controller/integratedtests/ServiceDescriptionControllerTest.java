package br.com.engdb.services.devportal.controller.integratedtests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.engdb.services.devportal.controller.ServiceDescriptionController;
import br.com.engdb.services.devportal.resources.ServiceDescriptionResource;
import br.com.engdb.services.devportal.service.ServiceDescriptionService;

/**
 * Junit para testes integrados da API para ServiceDescriptionController <BR><BR>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ServiceDescriptionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "test")
public class ServiceDescriptionControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ServiceDescriptionService service;

	@Test
	@WithMockUser(username = "test", roles = "READ")
	public void getServiceByIdTest() throws Exception {
		

		ServiceDescriptionResource serviceDescription = new ServiceDescriptionResource();
		serviceDescription.setDescription("Teste");
		
		given(service.getById("1")).willReturn(Optional.of(serviceDescription));

		mvc.perform(get("/api/v1/services-descriptions/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description", is(serviceDescription.getDescription())));

	}
	
	@Test
	@WithMockUser(username = "test", roles = "WHATEVER")
	public void getServiceDescriptionForbidden() throws Exception {
		
		given(service.getById("1")).willReturn(Optional.of(new ServiceDescriptionResource()));

		mvc.perform(get("/api/v1/services-descriptions/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());

	}
	
	@Test
	@WithMockUser(username = "test", roles = "READ")
	public void getServiceDescriptionNotFound() throws Exception {
		
		given(service.getById("1")).willReturn(Optional.of(new ServiceDescriptionResource()));

		mvc.perform(get("/api/v1/services-descriptions/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

}