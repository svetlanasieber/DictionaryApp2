package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepo;
import com.dictionaryapp.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepo languageRepo;

    public LanguageServiceImpl(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }

    @Override
    public void initLanguages() {
        if (this.languageRepo.count() != 0) {
            return;
        }

        Arrays.stream(LanguageEnum.values())
                .forEach(s -> {
                    Language language = new Language();
                    language.setName(s);
                    switch (s.getValue()) {
                        case "German":
                            language.setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
                            break;
                        case "Spanish":
                            language.setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
                            break;
                        case "French":
                            language.setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
                            break;
                        default:
                            language.setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
                            break;
                    }

                    this.languageRepo.save(language);
                });

    }

    @Override
    public Language findLanguage(LanguageEnum languageEnum) {
        return this.languageRepo.findByName(languageEnum).orElseThrow();
    }

    @Override
    public Language findStyleByStyleName(LanguageEnum styleName) {
        return this.languageRepo.findByName(styleName).orElseThrow();
    }
}
