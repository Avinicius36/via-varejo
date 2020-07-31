package br.com.engdb.services.devportal.resources;

import java.time.LocalDate;
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
public class ParameterResource {

	private Long id; 
	private String description; 
	private String value; 
	private boolean enabled;
	private LocalDate dataCadastro;
	private List<SubParameterResource> subParameters;
	
}
