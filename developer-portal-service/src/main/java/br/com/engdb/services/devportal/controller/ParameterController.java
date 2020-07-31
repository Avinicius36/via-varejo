package br.com.engdb.services.devportal.controller;

import java.util.List;

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
import br.com.engdb.services.devportal.resources.ParameterChangeResource;
import br.com.engdb.services.devportal.resources.ParameterPathResource;
import br.com.engdb.services.devportal.resources.ParameterResource;
import br.com.engdb.services.devportal.service.ParameterService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/parameter/")
public class ParameterController implements RestService {
	
	@Autowired
	private ParameterService parameterService;
	
	@GetMapping(path = "/{idParameter}")
	@ApiOperation(value = "Obtem um parametro cadastrado.")
	@PreAuthorize("hasRole('READ')")
	public ParameterResource get(@NonNull @PathVariable(value="idParameter") final Long id) throws EntityNotFoundException {
		return parameterService.getById(id)
				.orElseThrow(EntityNotFoundException::new);
	}
	
	@GetMapping(path = "/byDescription/{description}")
	@ApiOperation(value = "Obtem os parametros cadastrados com a descrição correspondente.")
	@PreAuthorize("hasRole('READ')")
	public List<ParameterResource> findByDescription(@NonNull @PathVariable(value="description") final String description) throws EntityNotFoundException {
		return parameterService.findByDescription(description);
	}
	
	@GetMapping(path = "/bySubParamValue/{value}")
	@ApiOperation(value = "Obtem os sub-parametros cadastrados com o valor correspondente.")
	@PreAuthorize("hasRole('READ')")
	public List<ParameterResource> findBySubParamValue(@NonNull @PathVariable(value="value") final String value) throws EntityNotFoundException {
		return parameterService.findBySubParamValue(value);
	}
	
	@PostMapping(path = "/")
	@ApiOperation(value = "Cria um novo parametro")
	@PreAuthorize("hasRole('WRITE')")
	public ParameterResource post(@NonNull @RequestBody final ParameterResource resource) throws BaseException{
		parameterService.save(resource);
		return resource;
	}
	
	@PatchMapping(path = "/{idParameter}")
	@ApiOperation(value = "Atualiza a descrição de um parametro cadastrado.")
	@PreAuthorize("hasRole('WRITE')")
	public void patch(@NonNull @PathVariable(value="idParameter") final Long id, @NonNull @RequestBody final ParameterPathResource resource) throws BaseException{
		parameterService.alterar(id, resource);
	}
	
	@GetMapping(path = "/")
	@ApiOperation(value = "Obtem todos os parametro cadastrados.")
	@PreAuthorize("hasRole('READ')")
	public List<ParameterResource> getAll() throws BaseException{
		return parameterService.findAllParameters();
	}
	
	@GetMapping(path = "/revisions/{idParameter}")
	@ApiOperation(value = "Obtem todos os parametro cadastrados.")
	@PreAuthorize("hasRole('READ')")
	public List<ParameterChangeResource> getAllRevisions(@NonNull @PathVariable(value="idParameter") final Long id) throws BaseException{
		return parameterService.getAllRevisions(id);
	}
	
}
