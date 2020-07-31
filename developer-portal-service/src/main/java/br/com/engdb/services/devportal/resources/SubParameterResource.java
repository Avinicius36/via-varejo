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
public class SubParameterResource {

	private Long id; 
	private String description; 
	private LocalDate dataCadastro;
	
}
