package com.trackpets.springboot.web.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.trackpets.springboot.web.app.models.dao.dto.UsuarioDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UsuarioDTO user = (UsuarioDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
