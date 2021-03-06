package br.com.engdb.services.devportal.resources;

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
public class EnvironmentPutPostResource {

	private String name; 
	private String description; 
	private Integer order;
	private Boolean active;
	
}
