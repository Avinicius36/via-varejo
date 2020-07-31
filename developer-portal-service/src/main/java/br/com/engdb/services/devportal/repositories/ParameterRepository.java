package br.com.engdb.services.devportal.repositories;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.engdb.services.devportal.service.documents.Parameter;

@Repository
@JaversSpringDataAuditable
public interface ParameterRepository extends MongoRepository<Parameter, Long>  {
	
	List<Parameter> findByDescricaoParametro (String descricaoParametro);
	
	@Query("{ subParameters : {valorParametro : ?0 }}")
	List<Parameter> findBySubParamValue (String value);
	
}
