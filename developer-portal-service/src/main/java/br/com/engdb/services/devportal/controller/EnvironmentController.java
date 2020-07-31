package br.com.engdb.services.devportal.controller;

import java.util.List;

import br.com.engdb.services.devportal.resources.CategoryPutPostResource;
import br.com.engdb.services.devportal.resources.EnvironmentPutPostResource;
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
import br.com.engdb.services.devportal.resources.EnvironmentResource;
import br.com.engdb.services.devportal.service.EnvironmentService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/environment/")
public class EnvironmentController implements RestService {

    @Autowired
    private EnvironmentService environmentService;

    @GetMapping(path = "/")
    @ApiOperation(value = "Obtem todos os ambientes cadastrados.")
    @PreAuthorize("hasRole('READ')")
    public List<EnvironmentResource> getAll() throws BaseException{
        return environmentService.findAllCategoriesSorted();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obtem um ambiente cadastrado.")
    @PreAuthorize("hasRole('READ')")
    public EnvironmentResource get(@NonNull @PathVariable(value="id") final String id) throws EntityNotFoundException {
        return environmentService.getById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(path = "/")
    @ApiOperation(value = "Cria um ambiente.")
    @PreAuthorize("hasRole('WRITE')")
    public EnvironmentResource post(@NonNull @RequestBody final EnvironmentPutPostResource resource) throws BaseException{
        return environmentService.create(resource);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza parcialmente um ambiente.")
    @PreAuthorize("hasRole('WRITE')")
    public EnvironmentResource patch(@NonNull @PathVariable(value="id") final String id, @NonNull @RequestBody final EnvironmentPutPostResource resource) throws BaseException{
        return environmentService.completeUpdate(id, resource);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deleta um ambiente cadastrado")
    @PreAuthorize("hasRole('WRITE')")
    public void delete(@NonNull @PathVariable(value="id") final String id) throws BaseException{
        environmentService.delete(id);
    }


}
