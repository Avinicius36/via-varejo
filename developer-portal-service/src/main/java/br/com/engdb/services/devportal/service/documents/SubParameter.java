package br.com.engdb.services.devportal.service.documents;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("subparameter")
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SubParameter {
	
	@Id
	private Long id;

	private String valorParametro;
	
	private LocalDate dataCadastro;
	
}