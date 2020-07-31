package br.com.engdb.services.devportal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.engdb.services.devportal.repositories.EnvironmentRepository;
import br.com.engdb.services.devportal.resources.SimpleEnvironmentResource;
import br.com.engdb.services.devportal.service.documents.Environment;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engdb.services.commons.exception.BaseException;
import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.commons.exception.EntityNotUniqueException;
import br.com.engdb.services.devportal.repositories.CategoryRepository;
import br.com.engdb.services.devportal.repositories.ServiceDescriptionRepository;
import br.com.engdb.services.devportal.repositories.predicate.ServiceDescriptionPredicate;
import br.com.engdb.services.devportal.resources.ServiceDescriptionPutPostResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionResource;
import br.com.engdb.services.devportal.resources.SimpleCategoryResource;
import br.com.engdb.services.devportal.service.documents.Category;
import br.com.engdb.services.devportal.service.documents.ServiceDescription;
import br.com.engdb.services.devportal.service.mappers.ServiceDescriptionPutPostResourceToResourceMapper;
import br.com.engdb.services.devportal.service.mappers.ServiceDescriptionResourceToDocumentMapper;
import br.com.engdb.services.devportal.service.mappers.ServiceDescriptionToResourceMapper;

@Service
public class ServiceDescriptionService {

	@Autowired
	private ServiceDescriptionPutPostResourceToResourceMapper postToResourceMapper;

	@Autowired
	private ServiceDescriptionRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private EnvironmentRepository environmentRepository;

	@Autowired
	private ServiceDescriptionToResourceMapper toResourceMapper;

	@Autowired
	private ServiceDescriptionResourceToDocumentMapper toDocumentMapper;

	public Page<ServiceDescriptionResource> getAll(Pageable page, Optional<String> category, Optional<String> search) throws BaseException {
		ServiceDescriptionPredicate predicate = new ServiceDescriptionPredicate();
		predicate.search(search);
		predicate.category(category);
		return repository.findAll(predicate.r(), page).map(toResourceMapper);
	}

	public Optional<ServiceDescriptionResource> getById(final String id) {

		return repository.findById(id).map(toResourceMapper);

	}

	public void updateServiceDescriptionCategory(final Category category) throws EntityNotFoundException {

		Stream<ServiceDescription> s = repository.findByCategoriesId(category.getId()).orElseThrow(EntityNotFoundException::new);

		List<ServiceDescription> list = s.collect(Collectors.toList());
		list.forEach(l -> l.getCategories().forEach(c -> {
			if (c.getId().equals(category.getId())) {
				c.setName(category.getName());
			}
		}));


		repository.saveAll(list);
	}

	public ServiceDescriptionResource create(final ServiceDescriptionPutPostResource postResource) throws BaseException {

		Optional<ServiceDescriptionPutPostResource> optional = Optional.of(postResource);

		List<SimpleCategoryResource> categories = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(postResource.getCategories())) {
			for (SimpleCategoryResource c : postResource.getCategories()) {
				Category category = categoryRepository.findById(c.getId()).orElseThrow(EntityNotFoundException::new);
				categories.add(new SimpleCategoryResource(category.getId(), category.getName()));
			}
		}

		ServiceDescriptionResource resource = optional.map(postToResourceMapper).get();
		resource.setId(UUID.randomUUID().toString());
		resource.setLastUpdate(new Date());
		resource.setCategories(categories);

		ServiceDescription document = Optional.of(resource).map(toDocumentMapper).get();

		return saveUpdate(document);
	}

	private ServiceDescriptionResource saveUpdate(final ServiceDescription doc) throws EntityNotUniqueException {
		try {
			return Optional.of(repository.save(doc)).map(toResourceMapper).get();
		} catch (DuplicateKeyException e) {
			throw new EntityNotUniqueException();
		}
	}

	public void delete(final String id) throws EntityNotFoundException {

		repository.findById(id).orElseThrow(EntityNotFoundException::new);
		repository.deleteById(id);

	}

	public Page<ServiceDescriptionResource> getAll(Pageable page, Optional<String> environment, Optional<String> search) throws BaseException {
		ServiceDescriptionPredicate predicate = new ServiceDescriptionPredicate();
		predicate.search(search);
		predicate.environment(environment);
		return repository.findAll(predicate.r(), page).map(toResourceMapper);
	}

	public void updateServiceDescriptionEnvironment(final Environment environment) throws EntityNotFoundException {

		Stream<ServiceDescription> s = repository.findByEnvironmentsId(environment.getId()).orElseThrow(EntityNotFoundException::new);

		List<ServiceDescription> list = s.collect(Collectors.toList());
		list.forEach(l -> l.getEnvironments().forEach(c -> {
			if (c.getId().equals(environment.getId())) {
				c.setName(environment.getName());
			}
		}));

		repository.saveAll(list);
	}

	public ServiceDescriptionResource create(final ServiceDescriptionPutPostResource postResource) throws BaseException {

		Optional<ServiceDescriptionPutPostResource> optional = Optional.of(postResource);

		List<SimpleEnvironmentResource> environments = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(postResource.getEnvironments())) {
			for (SimpleEnvironmentResource c : postResource.getEnvironments()) {
				Environment environment = environmentRepository.findById(c.getId()).orElseThrow(EntityNotFoundException::new);
				environments.add(new SimpleEnvironmentResource(environment.getId(), environment.getName()));
			}
		}

		ServiceDescriptionResource resource = optional.map(postToResourceMapper).get();
		resource.setId(UUID.randomUUID().toString());
		resource.setLastUpdate(new Date());
		resource.setEnvironments(environments);

		ServiceDescription document = Optional.of(resource).map(toDocumentMapper).get();

		return saveUpdate(document);

	}
}