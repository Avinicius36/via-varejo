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

import br.com.engdb.services.devportal.controller.ParameterController;
import br.com.engdb.services.devportal.resources.ParameterResource;
import br.com.engdb.services.devportal.service.ParameterService;

/**
 * Junit para testes integrados da API para campanhapremiacao <BR><BR>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ParameterController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "test")
public class ParameterControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ParameterService service;

	@Test
	@WithMockUser(username = "test", roles = "READ")
	public void getParam() throws Exception {
		
		ParameterResource parameterResource = new ParameterResource();
		parameterResource.setDescription("Teste");
		
		given(service.getById((long) 1)).willReturn(Optional.of(parameterResource));

		mvc.perform(get("/api/v1/parameter/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description", is(parameterResource.getDescription())));

	}
	
	@Test
	@WithMockUser(username = "test", roles = "WHATEVER")
	public void getParamForbidden() throws Exception {
		
		given(service.getById((long) 1)).willReturn(Optional.of(new ParameterResource()));

		mvc.perform(get("/api/v1/parameter/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());

	}
	
	@Test
	@WithMockUser(username = "test", roles = "READ")
	public void getParamNotFound() throws Exception {
		
		given(service.getById((long) 1)).willReturn(Optional.empty());

		mvc.perform(get("/api/v1/parameter/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

}