package br.com.engdb.services.devportal.repositories;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.engdb.services.devportal.service.documents.ServiceDescriptionDocumentation;

@Repository
public interface ServiceDescriptionDocumentationRepository extends MongoRepository<ServiceDescriptionDocumentation, String> {

	@Query("{$and : [{serviceDescriptionId: ?0}, {status : ?1}]}")
	Optional<ServiceDescriptionDocumentation> findByServiceDescIdAndStatus(String serviceDescId, String status);
	
	@Query("{$and : [{serviceDescriptionId: ?0}, {id : ?1}]}")
	Optional<ServiceDescriptionDocumentation> findByServiceDescIdAndDocId(String serviceDescId, String serviceDescDocId);
	
	@Query("{serviceDescriptionId: ?0}")
	Optional<Stream<ServiceDescriptionDocumentation>> findAllByServiceDescriptionId(String serviceDescId);
}
