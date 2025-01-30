package com.dictionaryapp.init;

import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.impl.WordServiceImpl;
import com.dictionaryapp.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {

    private final LanguageService languageService;
    private final UserServiceImpl userService;
    private final WordServiceImpl wordService;

    public FirstInit(LanguageService languageService,
                     UserServiceImpl userService,
                     WordServiceImpl wordService) {
        this.languageService = languageService;
        this.userService = userService;
        this.wordService = wordService;
    }

    @Override
    public void run(String... args) {
        this.userService.initAdmin();
        this.userService.initTest();
        this.languageService.initLanguages();
        this.wordService.addTestWords();
    }
}
