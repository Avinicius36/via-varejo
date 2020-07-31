package br.com.engdb.services.devportal.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.commons.exception.EntityNotUniqueException;
import br.com.engdb.services.devportal.repositories.CategoryRepository;
import br.com.engdb.services.devportal.resources.CategoryPutPostResource;
import br.com.engdb.services.devportal.resources.CategoryResource;
import br.com.engdb.services.devportal.service.documents.Category;
import br.com.engdb.services.devportal.service.mappers.CategoryDocumentToResourceMapper;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private CategoryDocumentToResourceMapper toResourceMapper;
	
	@Autowired
	private ServiceDescriptionService serviceDescription;
	
	public Optional<CategoryResource> getById(final String id) {
		return repository.findById(id).map(toResourceMapper);
	}
	
	public CategoryResource create(final CategoryPutPostResource resource) throws EntityNotUniqueException  {
		
		Category en = new Category();
		
		en.setId(UUID.randomUUID().toString());
		
		en.setName(resource.getName());	
		en.setActive(Optional.ofNullable(resource.getActive()).orElse(true));	
		en.setDescription(resource.getDescription());	
		en.setOrder(Optional.ofNullable(resource.getOrder()).orElseGet(() -> (int)repository.count() + 1));	
		
		return saveUpdate(en);
	}
	
	
	public void delete(final String id) throws EntityNotFoundException {
		repository.findById(id).orElseThrow(EntityNotFoundException::new);
		repository.deleteById(id);
	}
	
	public CategoryResource completeUpdate(final String id, final CategoryPutPostResource resource) throws EntityNotFoundException, EntityNotUniqueException  {
		
		Category en = repository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
		
		en.setName(resource.getName());	
		en.setActive(resource.getActive());	
		en.setDescription(resource.getDescription());	
		en.setOrder(resource.getOrder());	
		
		CategoryResource updatedResource = saveUpdate(en);
		
		serviceDescription.updateServiceDescriptionCategory(en);
		
		return updatedResource;
	}
	
	private CategoryResource saveUpdate(Category en) throws EntityNotUniqueException {
		try {
			return Optional.of(repository.save(en)).map(toResourceMapper).get();
		} catch (DuplicateKeyException e) {
			throw new EntityNotUniqueException();
		}
	}
	
	public List<CategoryResource> findAllCategoriesSorted() {
		return repository.findAll(Sort.by("order"))
					.stream()
					.map(toResourceMapper)
					.collect(Collectors.toList());
	}
	
}
