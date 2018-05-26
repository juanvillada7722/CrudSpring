/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Juan Villada
 */
public class UserValidation implements Validator {

    public JdbcTemplate jdbcTemplate;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> type) {
        return UserModel.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserModel usuarios = (UserModel) o;
    
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "required.name", "El campo Usuario es Obligatorio.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "required.email", "El campo Correo electrónico es Obligatorio.");

        if (!(usuarios.getEmail() != null && usuarios.getEmail().isEmpty())) {
            this.pattern = Pattern.compile(EMAIL_PATTERN);
            this.matcher = pattern.matcher(usuarios.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "correo.incorrect",
                        "El Correo electrónico " + usuarios.getEmail() + " no es válido");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "El campo contraseña es Obligatorio.");
    }

}
