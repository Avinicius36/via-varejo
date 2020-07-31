package br.com.engdb.services.devportal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.engdb.services.commons.controller.RestService;
import br.com.engdb.services.commons.exception.BaseException;
import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.devportal.resources.ServiceDescriptionPutPostResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionResource;
import br.com.engdb.services.devportal.service.ServiceDescriptionService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/services-descriptions/")
public class ServiceDescriptionController implements RestService {

	@Autowired
	ServiceDescriptionService serviceDescriptionService;

	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves service description by id.")
	@PreAuthorize("hasRole('READ')")
	public ServiceDescriptionResource getServiceById(@NonNull @PathVariable(value="id") final String id) throws EntityNotFoundException{
		return serviceDescriptionService.getById(id)
				.orElseThrow(EntityNotFoundException::new);
	}

	@GetMapping
	@ApiOperation(value = "Retrieves list of service description.")
	@PreAuthorize("hasRole('READ')")
	public Page<ServiceDescriptionResource> getAllService(
			@PageableDefault(size = 15, sort = { "name" }) Pageable pageable,
			@RequestParam("category") Optional<String> category,
			@RequestParam("search") Optional<String> search
			) throws BaseException{
		return serviceDescriptionService.getAll(pageable, category, search);
	}


	@PostMapping
	@ApiOperation(value = "Create new service description.")
	@PreAuthorize("hasRole('WRITE')")
	public ServiceDescriptionResource createService(@NonNull @RequestBody ServiceDescriptionPutPostResource resource) throws BaseException{

		return serviceDescriptionService.create(resource);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete service description by id.")
	@PreAuthorize("hasRole('WRITE')")
	public void deleteServiceById(@NonNull @PathVariable(value="id") final String id) throws BaseException{
		serviceDescriptionService.delete(id);
	}
}
