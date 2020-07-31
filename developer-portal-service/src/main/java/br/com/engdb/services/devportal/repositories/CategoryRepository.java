package br.com.engdb.services.devportal.repositories;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.engdb.services.devportal.service.documents.Category;

@Repository
@JaversSpringDataAuditable
public interface CategoryRepository extends MongoRepository<Category, String>  {
	
}
