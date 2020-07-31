package br.com.engdb.services.devportal.service.mappers;

import java.util.function.Function;

import org.mapstruct.Mapper;

import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationResource;
import br.com.engdb.services.devportal.service.documents.ServiceDescriptionDocumentation;

@Mapper(componentModel = "spring")
public interface ServiceDescriptionDocumentationDocumentToResourceMapper extends Function<ServiceDescriptionDocumentation, ServiceDescriptionDocumentationResource> {

    @Override
    ServiceDescriptionDocumentationResource apply(ServiceDescriptionDocumentation t);
    
}