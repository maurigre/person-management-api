package br.com.mgr.personapi.entrypoint.controller.v1.dto.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@ToString
public class PersonDto extends RepresentationModel<PersonDto> {

    private String id;

    @NotEmpty(message = "First name cannot be null ")
    @Size(min = 2, max = 100)
    private String firstName;

    @NotEmpty(message = "Last name cannot be null ")
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty(message = "Cpf name cannot be null ")
    @CPF
    private String cpf;

    @NotNull(message = "Date time cannot be null ")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Valid
    @NotEmpty
    private List<PhoneDto> phones;

}
