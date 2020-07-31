package br.com.engdb.services.devportal.service.mappers;

import java.util.function.Function;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.engdb.services.devportal.resources.ParameterResource;
import br.com.engdb.services.devportal.resources.SubParameterResource;
import br.com.engdb.services.devportal.service.documents.Parameter;
import br.com.engdb.services.devportal.service.documents.SubParameter;

@Mapper(componentModel = "spring")
public interface ParameterResourceToDocumentMapper extends Function<ParameterResource, Parameter> {
 
	@Mapping(target = "valorParametro", source = "value")
	@Mapping(target = "descricaoParametro", source = "description")
    @Override
    Parameter apply(ParameterResource t);
    
	@Mapping(target = "valorParametro", source = "description")
    SubParameter parameterListToResourceList(SubParameterResource parameter);
}