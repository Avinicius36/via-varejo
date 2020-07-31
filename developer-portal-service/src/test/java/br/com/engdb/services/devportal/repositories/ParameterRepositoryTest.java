package br.com.engdb.services.devportal.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.engdb.services.devportal.service.documents.Parameter;

@DataMongoTest
@RunWith(SpringRunner.class)
public class ParameterRepositoryTest {
	
	@Autowired
	private ParameterRepository parameterRepository;
	
	@Before
	public void setup() throws Exception {
		// given
        Parameter param = new Parameter();
        param.setId((long)1);
        param.setDescricaoParametro("Parametro 1");
        param.setDataCadastro(LocalDate.now());
        param.setValorParametro("Teste");
       
        // when
        parameterRepository.save(param);
    }
	
    @Test
    public void testFindByDescricaoParametro() {
        // then
        assertEquals(parameterRepository.findByDescricaoParametro("Parametro 1").size(), 1);
    }
}