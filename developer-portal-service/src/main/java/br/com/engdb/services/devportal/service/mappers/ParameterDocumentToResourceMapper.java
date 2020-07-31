package br.com.engdb.services.devportal.service.mappers;

import java.util.function.Function;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.engdb.services.devportal.resources.ParameterResource;
import br.com.engdb.services.devportal.resources.SubParameterResource;
import br.com.engdb.services.devportal.service.documents.Parameter;
import br.com.engdb.services.devportal.service.documents.SubParameter;

@Mapper(componentModel = "spring")
public interface ParameterDocumentToResourceMapper extends Function<Parameter, ParameterResource> {
 
	@Mapping(source = "valorParametro", target = "value")
	@Mapping(source = "descricaoParametro", target = "description")
    @Override
    ParameterResource apply(Parameter t);
    
	@Mapping(source = "valorParametro", target = "description")
    SubParameterResource parameterListToResourceList(SubParameter parameter);
}