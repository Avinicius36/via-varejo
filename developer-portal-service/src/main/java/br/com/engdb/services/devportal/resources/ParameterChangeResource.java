package br.com.engdb.services.devportal.resources;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParameterChangeResource  {

	private String author; 
	private LocalDateTime changeDate;
	private ParameterResource parameterResource;
	
}
