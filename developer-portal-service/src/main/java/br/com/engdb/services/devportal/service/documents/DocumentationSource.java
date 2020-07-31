package br.com.engdb.services.devportal.service.documents;

import javax.validation.constraints.NotNull;

import br.com.engdb.services.devportal.resources.DocumentationSourceResource.DocumentationSourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "sourceType", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DocumentationSource {
	
	@NotNull(message = "sourceType is compulsory")
	private DocumentationSourceType sourceType; 
	

}
