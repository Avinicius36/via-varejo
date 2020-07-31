package br.com.engdb.services.devportal.service.mappers;

import java.util.List;
import java.util.function.Function;

import org.mapstruct.Mapper;

import br.com.engdb.services.devportal.resources.ServiceDescriptionResource;
import br.com.engdb.services.devportal.resources.SimpleCategoryResource;
import br.com.engdb.services.devportal.service.documents.EmbeddedCategory;
import br.com.engdb.services.devportal.service.documents.ServiceDescription;

@Mapper(componentModel = "spring")
public interface ServiceDescriptionToResourceMapper extends Function<ServiceDescription, ServiceDescriptionResource> {
 
    @Override
    ServiceDescriptionResource apply(ServiceDescription t);

    List<SimpleCategoryResource> categoryListToResourceList(List<EmbeddedCategory> document);
    
}