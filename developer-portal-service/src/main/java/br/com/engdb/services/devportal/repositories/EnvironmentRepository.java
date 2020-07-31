package br.com.engdb.services.devportal.repositories;

import br.com.engdb.services.devportal.service.documents.Environment;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface EnvironmentRepository extends MongoRepository<Environment, String>  {

}