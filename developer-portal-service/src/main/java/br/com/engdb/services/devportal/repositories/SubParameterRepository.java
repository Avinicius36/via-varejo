package br.com.engdb.services.devportal.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.engdb.services.devportal.service.documents.SubParameter;

@Repository
public interface SubParameterRepository extends MongoRepository<SubParameter, Long>  {
	
}
