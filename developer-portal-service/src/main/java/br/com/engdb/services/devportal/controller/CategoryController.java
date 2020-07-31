package br.com.engdb.services.devportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engdb.services.commons.controller.RestService;
import br.com.engdb.services.commons.exception.BaseException;
import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.devportal.resources.CategoryPutPostResource;
import br.com.engdb.services.devportal.resources.CategoryResource;
import br.com.engdb.services.devportal.service.CategoryService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController implements RestService {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path = "/")
	@ApiOperation(value = "Obtem todas as categorias cadastradas.")
	@PreAuthorize("hasRole('READ')")
	public List<CategoryResource> getAll() throws BaseException{
		return categoryService.findAllCategoriesSorted();
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Obtem uma caregoria cadastrada.")
	@PreAuthorize("hasRole('READ')")
	public CategoryResource get(@NonNull @PathVariable(value="id") final String id) throws EntityNotFoundException {
		return categoryService.getById(id)
				.orElseThrow(EntityNotFoundException::new);
	}
	
	@PostMapping(path = "/")
	@ApiOperation(value = "Cria uma caregoria.")
	@PreAuthorize("hasRole('WRITE')")
	public CategoryResource post(@NonNull @RequestBody final CategoryPutPostResource resource) throws BaseException{
		return categoryService.create(resource);
	}
	
	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Atualiza parcialmente uma categoria.")
	@PreAuthorize("hasRole('WRITE')")
	public CategoryResource patch(@NonNull @PathVariable(value="id") final String id, @NonNull @RequestBody final CategoryPutPostResource resource) throws BaseException{
		return categoryService.completeUpdate(id, resource);
	}
	
	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Deleta uma caregoria cadastrada.")
	@PreAuthorize("hasRole('WRITE')")
	public void delete(@NonNull @PathVariable(value="id") final String id) throws BaseException{
		categoryService.delete(id);
	}
	
	
}
