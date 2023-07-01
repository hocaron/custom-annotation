package com.hocaron.customannotation.atleastone;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtLeastOneValidator implements ConstraintValidator<AtLeastOne, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        var fields = value.getClass().getDeclaredFields();

        for (var field : fields) {
            field.setAccessible(true);
            try {
                var fieldValue = field.get(value);
                if (fieldValue != null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                log.error("[isValid] {}", e.getStackTrace(), e);
            }
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("입력 값이 최소 1개 이상 이어야 합니다.")
                .addConstraintViolation();

        return false;
    }
}
