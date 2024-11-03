package kz.astana.dating.app.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Profile {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String about;
    private LocalDate birthDate;
    private Gender gender;
    private Status status;
    private String password;
}
