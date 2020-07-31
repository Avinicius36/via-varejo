package br.com.engdb.services.devportal.service.mappers;

import java.util.function.Function;

import org.mapstruct.Mapper;

import br.com.engdb.services.devportal.resources.ServiceDescriptionResource;
import br.com.engdb.services.devportal.service.documents.ServiceDescription;

@Mapper(componentModel = "spring")
public interface ServiceDescriptionDocumentToResourceMapper extends Function<ServiceDescription, ServiceDescriptionResource> {

    @Override
    ServiceDescriptionResource apply(ServiceDescription t);
    
}