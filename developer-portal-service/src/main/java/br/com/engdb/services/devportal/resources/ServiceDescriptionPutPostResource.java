package br.com.engdb.services.devportal.resources;

import java.time.LocalDate;
import java.util.List;

import br.com.engdb.services.devportal.resources.ServiceDescriptionResource.ServiceDescriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptionPutPostResource {
	
	private String name; 
    private String description; 
    private String version; 
    private ServiceDescriptionStatus status;
    private List<SimpleCategoryResource> categories;
    private List<SimpleEnvironmentResource> environments;
    private String owner; 
    private List<String> keywords;
    private List<String> tags;
    private DocumentationSourceResource documentationSource;
    private LocalDate lastUpdate;
	
}

