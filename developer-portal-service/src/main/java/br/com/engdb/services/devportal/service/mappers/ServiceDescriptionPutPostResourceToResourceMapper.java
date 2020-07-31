package br.com.engdb.services.devportal.service.mappers;

import java.util.function.Function;

import org.mapstruct.Mapper;

import br.com.engdb.services.devportal.resources.ServiceDescriptionPutPostResource;
import br.com.engdb.services.devportal.resources.ServiceDescriptionResource;

@Mapper(componentModel = "spring")
public interface ServiceDescriptionPutPostResourceToResourceMapper extends Function<ServiceDescriptionPutPostResource, ServiceDescriptionResource> {

	@Override
	ServiceDescriptionResource apply(ServiceDescriptionPutPostResource t);
		    

}