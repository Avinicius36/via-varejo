package br.com.engdb.services.devportal.resources;

import java.util.Date;
import java.util.List;

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
public class ServiceDescriptionResource {

	public enum ServiceDescriptionStatus {DRAFT, UNPUBLISHED, PUBLISHED};

	private String id; 
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
	private Date lastUpdate;

}

