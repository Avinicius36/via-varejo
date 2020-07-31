package br.com.engdb.services.devportal.service.mappers;

import java.util.List;
import java.util.function.Function;

import org.mapstruct.Mapper;

import br.com.engdb.services.devportal.resources.CategoryResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionResource;
import br.com.engdb.services.devportal.resources.SimpleCategoryResource;
import br.com.engdb.services.devportal.service.documents.Category;
import br.com.engdb.services.devportal.service.documents.EmbeddedCategory;
import br.com.engdb.services.devportal.service.documents.ServiceDescription;

@Mapper(componentModel = "spring")
public interface ServiceDescriptionResourceToDocumentMapper extends Function<ServiceDescriptionResource, ServiceDescription> {

    @Override
    ServiceDescription apply(ServiceDescriptionResource t);
    
    List<EmbeddedCategory> toEmbeddedCategory(List<SimpleCategoryResource> resource);
    
    
}