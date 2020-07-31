package br.com.engdb.services.devportal.service.documents;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.engdb.services.devportal.resources.ServiceDescriptionResource.ServiceDescriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("service_description")
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescription {
	
	@Id
	@NotNull(message = "ID is compulsory")
	private String id; 
	
	@NotNull(message = "Name is compulsory")
	private String name; 
	
	private String description; 
	
	private String version; 
	
	private ServiceDescriptionStatus status;
	
	private List<EmbeddedCategory> categories;

	private List<EmbeddedEnvironment> environments;
	
	private String owner; 
	
	private List<String> keywords;
	
	private List<String> tags;
	
	private DocumentationSource documentationSource;
	
	private Date lastUpdate;

}
