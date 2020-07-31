package br.com.engdb.services.devportal.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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

import br.com.engdb.services.devportal.repositories.ParameterRepository;
import br.com.engdb.services.devportal.resources.ParameterPathResource;
import br.com.engdb.services.devportal.service.documents.Parameter;

/**
 * Junit para testes unitário do serviço {@link ParameterService}
 */
@RunWith(MockitoJUnitRunner.class)
public class ParameterServiceTest {

	@InjectMocks
	private ParameterService service;

    @Mock
    private ParameterRepository parameterRepository;
    
    @Before
    public void init() {
        //Initialize objects annotated with @Mock, @Captor and @Spy.
        MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void alterarTest() throws Exception {
		// Mocka os metodos do parameterRepository para a condução do teste
		Mockito.when(parameterRepository.findById((long) 1))
  			.thenReturn(Optional.of(new Parameter()));
		
		Mockito.when(parameterRepository.save(any(Parameter.class)))
	      		.thenReturn(null);
		
		// Preenche os dados de entrada para o teste
		ParameterPathResource paramPath = new ParameterPathResource();
		paramPath.setDescription("Teste");
		
		// Executa o teste
		service.alterar((long) 1, paramPath);
		
		// Captura o que foi passado para o save do parameterRepository e checka se foi chamado apenas uma vez
		final ArgumentCaptor<Parameter> captor = ArgumentCaptor.forClass(Parameter.class);
		Mockito.verify(parameterRepository, times(1)).save(captor.capture());

		// Compara para ver se os valores foram alterados conforme esperado
		Parameter parameterDocument = captor.getAllValues().get(0);
	    assertEquals("Teste", parameterDocument.getDescricaoParametro());
	    assertNull(parameterDocument.getId());
	    assertNull(parameterDocument.getDataCadastro());
	    assertNull(parameterDocument.getValorParametro());
	    assertNull(parameterDocument.getSubParameters());
		
	}

}