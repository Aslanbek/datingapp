package kz.astana.dating.app.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private final List<String> errors = new ArrayList<>();

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void addError(String code) {
        errors.add(code);
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
}
