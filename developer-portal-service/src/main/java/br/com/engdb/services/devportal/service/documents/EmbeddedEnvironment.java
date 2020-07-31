package br.com.engdb.services.devportal.service.documents;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class EmbeddedEnvironment
{
    @Id
    @NotNull(message = "ID is compulsory")
    private String id;

    private String name;

}
