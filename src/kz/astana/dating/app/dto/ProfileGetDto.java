package kz.astana.dating.app.dto;

import kz.astana.dating.app.model.Gender;
import kz.astana.dating.app.model.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileGetDto {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String about;
    private LocalDate birthDate;
    private Gender gender;

    private Integer age;

    private Status status;
}
