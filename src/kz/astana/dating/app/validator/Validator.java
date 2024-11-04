package kz.astana.dating.app.validator;

public interface Validator<T> {
    ValidationResult validate(T obj);
}
