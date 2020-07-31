package br.com.engdb.services.devportal.service.documents;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document("parameter")
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
public class Parameter {
	
	@Id
	private Long id;

	private String valorParametro;
	
	private String descricaoParametro;
	
	private LocalDate dataCadastro;
	
	@DBRef
	private List<SubParameter> subParameters;
	
}