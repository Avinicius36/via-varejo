package br.com.engdb.services.devportal.service.documents;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.engdb.services.devportal.resources.ServiceDescriptionDocumentationResource.ServiceDescriptionDocumentationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("service_documentation")
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptionDocumentation {

	@Id
	@NotNull(message = "ID is compulsory")
	private String id; 

	private String fileName;
	
	@NotNull(message = "ServiceDescription ID is compulsory")
	private String serviceDescriptionId;
	
	@NotNull(message = "Content is compulsory")
	@Field(name = "contentBinary")
	private Binary content;
	
	private LocalDate creationDate;
	
	private ServiceDescriptionDocumentationStatus status;

}
