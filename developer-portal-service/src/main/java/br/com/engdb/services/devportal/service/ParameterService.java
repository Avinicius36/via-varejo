package br.com.engdb.services.devportal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.javers.core.Javers;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engdb.services.commons.exception.BusinessException;
import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.devportal.repositories.ParameterRepository;
import br.com.engdb.services.devportal.repositories.SubParameterRepository;
import br.com.engdb.services.devportal.resources.ParameterChangeResource;
import br.com.engdb.services.devportal.resources.ParameterPathResource;
import br.com.engdb.services.devportal.resources.ParameterResource;
import br.com.engdb.services.devportal.service.documents.Parameter;
import br.com.engdb.services.devportal.service.mappers.ParameterResourceToDocumentMapper;
import br.com.engdb.services.devportal.service.mappers.ParameterDocumentToResourceMapper;

@Service
public class ParameterService {

	@Autowired
	private ParameterRepository repository;
	
	@Autowired
	private SubParameterRepository repositorySubParam;
	
	@Autowired
	private Javers javers;
	
	@Autowired 
	private ParameterDocumentToResourceMapper toResourceMapper;
	
	private ParameterResourceToDocumentMapper toDocumentMapper;
	
	public Optional<ParameterResource> getById(final Long idParametro) {
		return repository.findById(idParametro).map(toResourceMapper);
	}
	
	public List<ParameterResource> findByDescription(final String description) {
		return repository.findByDescricaoParametro(description)
				.stream()
				.map(toResourceMapper)
				.collect(Collectors.toList());
	}
	
	public List<ParameterResource> findBySubParamValue(final String value) {
		return repository.findBySubParamValue(value)
				.stream()
				.map(toResourceMapper)
				.collect(Collectors.toList());
	}
	
	public List<ParameterChangeResource> getAllRevisions(final Long idParametro) {
		
		JqlQuery jqlQuery = QueryBuilder.byInstanceId(idParametro, Parameter.class).build();

		List<Shadow<Parameter>> shadows = javers.findShadows(jqlQuery);
		return shadows.stream()
				.map(e -> new ParameterChangeResource(e.getCommitMetadata().getAuthor(), e.getCommitMetadata().getCommitDate(), toResourceMapper.apply(e.get()))) 
				.collect(Collectors.toList());
	}
	
	public void save(final ParameterResource resource) {
		
		//Codigo apenas para teste, pode ser removido
		Parameter en = toDocumentMapper.apply(resource);
		
		en.getSubParameters().forEach(repositorySubParam::save);
		
		repository.save(en); 
	}
	
	public void alterar(final Long id, final ParameterPathResource resource) throws BusinessException {
		
		Parameter en =  repository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
		
		en.setDescricaoParametro(resource.getDescription());
		
		repository.save(en); 
	}
	
	public List<ParameterResource> findAllParameters() {
		return repository.findAll()
					.stream()
					.map(toResourceMapper)
					.collect(Collectors.toList());
	}
	
}
