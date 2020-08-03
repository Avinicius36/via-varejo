package br.com.engdb.services.devportal.service;

import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.commons.exception.EntityNotUniqueException;
import br.com.engdb.services.devportal.repositories.EnvironmentRepository;
import br.com.engdb.services.devportal.resources.EnvironmentPutPostResource;
import br.com.engdb.services.devportal.resources.EnvironmentResource;
import br.com.engdb.services.devportal.service.documents.Environment;
import br.com.engdb.services.devportal.service.mappers.EnvironmentDocumentToResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnvironmentService {

    @Autowired
    private EnvironmentRepository repository;

    @Autowired
    private EnvironmentDocumentToResourceMapper toResourceMapper;

    @Autowired
    private ServiceDescriptionService serviceDescription;

    public Optional<EnvironmentResource> getById(final String id) {
        return repository.findById(id).map(toResourceMapper);
    }

    public EnvironmentResource create(final EnvironmentPutPostResource resource) throws EntityNotUniqueException {

        Environment en = new Environment();

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

    public EnvironmentResource completeUpdate(final String id, final EnvironmentPutPostResource resource) throws EntityNotFoundException, EntityNotUniqueException  {

        Environment en = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        en.setName(resource.getName());
        en.setActive(resource.getActive());
        en.setDescription(resource.getDescription());
        en.setOrder(resource.getOrder());

        EnvironmentResource updatedResource = saveUpdate(en);

        serviceDescription.updateServiceDescriptionEnvironment(en);

        return updatedResource;
    }

    private EnvironmentResource saveUpdate(Environment en) throws EntityNotUniqueException {
        try {
            return Optional.of(repository.save(en)).map(toResourceMapper).get();
        } catch (DuplicateKeyException e) {
            throw new EntityNotUniqueException();
        }
    }

    public List<EnvironmentResource> findAllCategoriesSorted() {
        return repository.findAll(Sort.by("order"))
                .stream()
                .map(toResourceMapper)
                .collect(Collectors.toList());
    }

}

