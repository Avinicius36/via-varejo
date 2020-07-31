package br.com.engdb.services.devportal.service.mappers;

import java.util.function.Function;

import org.mapstruct.Mapper;

import br.com.engdb.services.devportal.resources.CategoryResource;
import br.com.engdb.services.devportal.service.documents.Category;

@Mapper(componentModel = "spring")
public interface CategoryDocumentToResourceMapper extends Function<Category, CategoryResource> {
 
    @Override
    CategoryResource apply(Category t);
    
}