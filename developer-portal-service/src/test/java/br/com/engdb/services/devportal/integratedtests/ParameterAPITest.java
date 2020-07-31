package br.com.engdb.services.devportal.integratedtests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.engdb.services.devportal.DevPortalApplication;

/**
 * Junit para testes integrados da API para campanhapremiacao <BR><BR>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.MOCK,
  classes = DevPortalApplication.class)
@AutoConfigureMockMvc
public class ParameterAPITest {

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(username = "test", roles = {"WRITE", "READ" })
	public void simpleParameterCreateAndGet() throws Exception {
		
		mvc.perform(post("/api/v1/parameter/")
				.content(" {\"id\":1,\"description\":\"desc\",\"value\":\"val\",\"enabled\":true,\"dataCadastro\":\"2019-01-01\",\"subParameters\":[]}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		mvc.perform(get("/api/v1/parameter/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description", is("desc")));

	}
	
}