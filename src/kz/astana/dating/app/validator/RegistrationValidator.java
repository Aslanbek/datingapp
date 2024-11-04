package kz.astana.dating.app.validator;


import kz.astana.dating.app.dao.ProfileDao;
import kz.astana.dating.app.dto.RegistrationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

import static kz.astana.dating.app.utils.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationValidator implements Validator<RegistrationDto> {
    private static final RegistrationValidator INSTANCE = new RegistrationValidator();

    public static RegistrationValidator getInstance() {
        return INSTANCE;
    }

    private final ProfileDao dao = ProfileDao.getInstance();

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public ValidationResult validate(RegistrationDto dto) {
        ValidationResult result = new ValidationResult();
        if (isBlank(dto.getEmail()) || !VALID_EMAIL_ADDRESS_REGEX.matcher(dto.getEmail()).matches()) {
            result.addError("error.email.invalid");
        } else if (dao.getAllEmails().contains(dto.getEmail())) {
            result.addError("error.email.exist");
        }
        if (isBlank(dto.getPassword())) {
            result.addError("error.password.invalid");
        }
        return result;
    }
}
