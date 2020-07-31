package br.com.engdb.services.devportal.resources;

import java.time.LocalDate;

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
public class ServiceDescriptionDocumentationResource {
	
	public enum ServiceDescriptionDocumentationStatus {CURRENT, INACTIVE};

	private String id; 
	private String serviceDescriptionId;
	private String fileName;
	private LocalDate creationDate;
	private ServiceDescriptionDocumentationStatus status;
	
}
