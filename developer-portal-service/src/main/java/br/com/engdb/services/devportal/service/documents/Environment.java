package br.com.engdb.services.devportal.service.documents;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("environment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Environment {

    @Id
    @NotNull(message = "ID is compulsory")
    private String id;

    @Indexed(unique=true)
    @NotNull(message = "User Name is compulsory")
    private String name;

    @NotNull(message = "Description is compulsory")
    private String description;

    @NotNull(message = "Order is compulsory")
    private Integer order;

    @NotNull(message = "Active is compulsory")
    private Boolean active;

}
