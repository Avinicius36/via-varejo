package br.com.engdb.services.devportal.service.mappers;

import java.util.function.Function;

import br.com.engdb.services.devportal.resources.EnvironmentResource;
import org.mapstruct.Mapper;

import sun.tools.java.Environment;

@Mapper(componentModel = "spring")
public interface EnvironmentDocumentToResourceMapper extends Function<Environment, EnvironmentResource> {

    @Override
    EnvironmentResource apply(Environment t);

}
