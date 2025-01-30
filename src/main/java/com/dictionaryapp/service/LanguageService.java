package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;

public interface LanguageService {

    void initLanguages();

    Language findLanguage(LanguageEnum style);

    Language findStyleByStyleName(LanguageEnum styleName);
}
