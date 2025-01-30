package com.dictionaryapp.validation;

import com.dictionaryapp.service.impl.WordServiceImpl;
import com.dictionaryapp.validation.annotation.UniquePerformer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniquePerformerValidator implements ConstraintValidator<UniquePerformer, String> {

    private final WordServiceImpl songService;

    public UniquePerformerValidator(WordServiceImpl songService) {
        this.songService = songService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
