package br.com.engdb.services.devportal.repositories;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.engdb.services.devportal.service.documents.ServiceDescription;

@Repository
public interface ServiceDescriptionRepository extends MongoRepository<ServiceDescription, String>, QuerydslPredicateExecutor<ServiceDescription>{
	
	@Query("{'categories': {$elemMatch: {name: ?0}}}")
	Page<ServiceDescription> findByCategoriesName(String categoryName, Pageable pageable);
	
	@Query("{'categories': {$elemMatch: {id: ?0}}}")
	Optional<Stream<ServiceDescription>> findByCategoriesId(String categoryId);
	
	@Query("{$or : [{name: ?0}, {description: ?0}, {tags: ?0}]}")
	Optional<Page<ServiceDescription>> findBySearchWord(String searchWord, Pageable pageable);
	
	@Query("{$and: [ {categories: {$elemMatch: {name: ?0}}},{$or : [{name: ?1}, {description: ?1}, {tags: ?1}]}]}")
	Page<ServiceDescription> findByCategoriesAndSearchWord(String categoryName, String searchWord, Pageable pageable);

	@Query("{'environment': {$elemMatch: {name: ?0}}}")
	Page<ServiceDescription> findByEnvironmentsName(String environmentName, Pageable pageable);

	@Query("{'categories': {$elemMatch: {id: ?0}}}")
	Optional<Stream<ServiceDescription>> findByEnvironmentsId(String environmentId);

	@Query("{$and: [ {categories: {$elemMatch: {name: ?0}}},{$or : [{name: ?1}, {description: ?1}, {tags: ?1}]}]}")
	Page<ServiceDescription> findByEnvironmentsAndSearchWord(String environmentName, String searchWord, Pageable pageable);
}
