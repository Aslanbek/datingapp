package kz.astana.dating.app.validator;

import kz.astana.dating.app.dto.ProfileUpdateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static kz.astana.dating.app.utils.DateTimeUtils.getAge;
import static kz.astana.dating.app.utils.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileUpdateValidator implements Validator<ProfileUpdateDto> {


    public static final ProfileUpdateValidator INSTANCE = new ProfileUpdateValidator();

    public static ProfileUpdateValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult validate(ProfileUpdateDto dto) {
        ValidationResult result = new ValidationResult();
        if (dto.getBirthDate() != null) {
            int age = getAge(dto.getBirthDate());
            if (age < 18 || age > 100) {
                result.addError("error.age.invalid");
            }
        }

/*        if (isBlank(dto.getName())) {
            result.addError("error.name.invalid");
        }*/
        return result;
    }
}
