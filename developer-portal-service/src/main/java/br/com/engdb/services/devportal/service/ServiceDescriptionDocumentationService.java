package br.com.engdb.services.devportal.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import br.com.engdb.services.commons.exception.BusinessException;
import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.commons.exception.EntityNotUniqueException;
import br.com.engdb.services.devportal.repositories.ServiceDescriptionDocumentationRepository;
import br.com.engdb.services.devportal.repositories.ServiceDescriptionRepository;
import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationPathResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationResource.ServiceDescriptionDocumentationStatus;
import br.com.engdb.services.devportal.service.documents.ServiceDescriptionDocumentation;
import br.com.engdb.services.devportal.service.mappers.ServiceDescriptionDocumentationDocumentToResourceMapper;

@Service
public class ServiceDescriptionDocumentationService {

	@Autowired
	private ServiceDescriptionRepository serviceDescriptionRepository;
	
	@Autowired
	private ServiceDescriptionDocumentationRepository repository;

	@Autowired
	private ServiceDescriptionDocumentationDocumentToResourceMapper toResourceMapper;
	
	@Autowired
	private ObjectMapper objectMapper;


	public Optional<Binary> getContentByServiceDescriptionIdCurrent(final String serviceDescriptionId)  {
		return repository.findByServiceDescIdAndStatus(serviceDescriptionId, ServiceDescriptionDocumentationStatus.CURRENT.toString())
				.map(e -> e.getContent());
	}

	public Optional<ServiceDescriptionDocumentationResource> getByServiceDescIdAndDocId(final String serviceDescriptionId, final String serviceDescriptionDocId){

		return repository.findByServiceDescIdAndDocId(serviceDescriptionId, serviceDescriptionDocId)
				.map(toResourceMapper);

	}

	public List<ServiceDescriptionDocumentationResource> getAllByServiceDescId(final String serviceDescriptionId) throws EntityNotFoundException{

		List<ServiceDescriptionDocumentationResource> serviceDescriptionDocs = null;

		Stream<ServiceDescriptionDocumentation> stream = repository.findAllByServiceDescriptionId(serviceDescriptionId).orElseThrow(EntityNotFoundException::new);

		serviceDescriptionDocs = stream.map(toResourceMapper).collect(Collectors.toList());


		return serviceDescriptionDocs;
	}

	public ServiceDescriptionDocumentationResource createServiceDescDocumentation(byte[] document, final String serviceDescriptionId) throws IOException, BusinessException {

		// Convert para JSON se não irá retornar o próprio conteudo 
		String json = convertYamlToJson(new String(document));
		
		if (!idValidJson(json)) {
			throw new BusinessException(400, "File content is not a valid JSON nor a valid YML");
		}
		
		// Valida se existe o Service Description
		serviceDescriptionRepository.findById(serviceDescriptionId).orElseThrow(EntityNotFoundException::new);
		
		//TODO Colocar logica de desativar demais documents e colocar validação do swagger Sprint 2
		repository.deleteAll(repository.findAllByServiceDescriptionId(serviceDescriptionId).orElse(Stream.empty()).collect(Collectors.toList()));

		ServiceDescriptionDocumentation serviceDocumentation = new ServiceDescriptionDocumentation();

		serviceDocumentation.setId(UUID.randomUUID().toString());
		serviceDocumentation.setServiceDescriptionId(serviceDescriptionId);
		serviceDocumentation.setFileName("swagger.json");
		serviceDocumentation.setContent(new Binary(BsonBinarySubType.BINARY, json.getBytes()));
		serviceDocumentation.setCreationDate(LocalDate.now());
		serviceDocumentation.setStatus(ServiceDescriptionDocumentationStatus.CURRENT);		

		return saveUpdate(serviceDocumentation);

	}

	public ServiceDescriptionDocumentationResource updateServiceDescDocumentation(final ServiceDescriptionDocumentationPathResource requestBody, final String serviceDescriptionId, final String serviceDescriptionDocId) throws Exception {

		ServiceDescriptionDocumentation serviceDocumentation = new ServiceDescriptionDocumentation();
		serviceDocumentation = 	repository
				.findByServiceDescIdAndDocId(serviceDescriptionId, serviceDescriptionDocId.toString())
				.orElseThrow(EntityNotFoundException::new);

		if(ServiceDescriptionDocumentationStatus.CURRENT.name().equals(requestBody.getStatus().name())) {
			serviceDocumentation.setStatus(ServiceDescriptionDocumentationStatus.CURRENT);
		} else {
			serviceDocumentation.setStatus(ServiceDescriptionDocumentationStatus.INACTIVE);
		}

		return saveUpdate(serviceDocumentation);
	}

	private ServiceDescriptionDocumentationResource saveUpdate(final ServiceDescriptionDocumentation doc) throws EntityNotUniqueException {
		try {
			return Optional.of(repository.save(doc)).map(toResourceMapper).get();
		} catch (DuplicateKeyException e) {
			throw new EntityNotUniqueException();
		}
	}

	private String convertYamlToJson(String yaml) throws JsonMappingException, JsonProcessingException {
		ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
		Object obj = yamlReader.readValue(yaml, Object.class);

		ObjectMapper jsonWriter = new ObjectMapper();
		return jsonWriter.writeValueAsString(obj);
	}

	public boolean idValidJson(final String json) {
	    try{ 
	    	JsonNode jsonNodeRoot = objectMapper.readTree(json);
	    	return jsonNodeRoot.elements().hasNext();
	    } catch(JsonProcessingException e){
	        return false;
	    }
	}
	
}
