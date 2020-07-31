package br.com.engdb.services.devportal.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engdb.services.commons.controller.RestService;
import br.com.engdb.services.commons.exception.BaseException;
import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationPathResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationResource;
import br.com.engdb.services.devportal.service.ServiceDescriptionDocumentationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/services-descriptions/documentations/")
@Slf4j
public class ServiceDescriptionDocumentationController implements RestService {

	@Autowired
	ServiceDescriptionDocumentationService serviceDocumentationService;

	@GetMapping(value="/{service-description-id}/current", produces = "application/json")
	@ApiOperation(value = "Retrieves current service description documentation for provided service description id.")
	@PreAuthorize("hasRole('READ')")
	public String getCurrentDocumentation(@NonNull @PathVariable(value="service-description-id") final String serviceDescriptionId) throws EntityNotFoundException{
		return serviceDocumentationService.getContentByServiceDescriptionIdCurrent(serviceDescriptionId)
				.map(e -> new String(e.getData()))
				.orElseThrow(EntityNotFoundException::new);

	}

	@GetMapping("/{service-description-id}/{service-documentation-id}")
	@ApiOperation(value = "Retrieves service description documentation for the provided service description id and service documentation id.")
	@PreAuthorize("hasRole('READ')")
	public ServiceDescriptionDocumentationResource getById(@NonNull @PathVariable(value="service-description-id") final String serviceDescriptionId,
			@NonNull @PathVariable(value="service-documentation-id") final String serviceDescriptionDocId) throws EntityNotFoundException{

		return serviceDocumentationService
				.getByServiceDescIdAndDocId(serviceDescriptionId, serviceDescriptionDocId)
				.orElseThrow(EntityNotFoundException::new);

	}

	@GetMapping("/{service-description-id}")
	@ApiOperation(value = "Retrieves all service description documentation by service description id.")
	@PreAuthorize("hasRole('READ')")
	public List<ServiceDescriptionDocumentationResource> getAllByServiceDescriptionId(@NonNull @PathVariable(value="service-description-id") final String serviceDescriptionId) throws BaseException{

		return serviceDocumentationService.getAllByServiceDescId(serviceDescriptionId);

	}

	@PostMapping("/{service-description-id}")
	@ApiOperation(value = "Create new service description documentation.")
	@PreAuthorize("hasRole('WRITE')")
	public ServiceDescriptionDocumentationResource create(@NotNull @RequestBody byte[] swagger, 
			@NonNull @PathVariable(value="service-description-id") final String serviceDescriptionId) throws BaseException{
		try {
			log.info(new String(swagger));
			return serviceDocumentationService.createServiceDescDocumentation(swagger, serviceDescriptionId);
		} catch (IOException e) {
			log.error("Error reading file");
			throw new RuntimeException(e);
		}
	}

	@PatchMapping(path = "/{service-description-id}/{service-documentation-id}")
	@ApiOperation(value = "Updates the status of a service description documentation.")
	@PreAuthorize("hasRole('WRITE')")
	public ServiceDescriptionDocumentationResource patch(@NonNull @RequestBody final ServiceDescriptionDocumentationPathResource requestBody,  
			@NonNull @PathVariable(value="service-description-id") final String serviceDescriptionId,
			@NonNull @PathVariable(value="service-documentation-id") final String serviceDescriptionDocId) throws Exception{

		return serviceDocumentationService.updateServiceDescDocumentation(requestBody, serviceDescriptionId, serviceDescriptionDocId);

	}
}
