package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.LanguageRepo;
import com.dictionaryapp.repo.WordRepo;
import com.dictionaryapp.repo.UserRepo;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepo wordRepo;
    private final LanguageService languageService;
    private final UserServiceImpl userService;
    private final UserRepo userRepo;
    private final LanguageRepo languageRepo;

    public WordServiceImpl(WordRepo wordRepo,
                           LanguageService languageService,
                           UserServiceImpl userService,
                           UserRepo userRepo,
                           LanguageRepo languageRepo) {
        this.wordRepo = wordRepo;
        this.languageService = languageService;
        this.userService = userService;
        this.userRepo = userRepo;
        this.languageRepo = languageRepo;
    }

    @Override
    public void addWord(AddWordDTO addWordDTO, Long id) {
        Word word = new Word();
        Language language = this.languageService.findLanguage(addWordDTO.getLanguage());
        User userById = userService.findUserById(id).orElse(null);


        word.setTerm(addWordDTO.getTerm())
                .setTranslation(addWordDTO.getTranslation())
                .setExample(addWordDTO.getExample())
                .setInputDate(addWordDTO.getInputDate())
                .setLanguage(language)
                .setAddedBy(userById);

        Set<Word> userByIdAssignedWords = userById.getAddedWords();
        userByIdAssignedWords.add(word);
        userById.setAddedWords(userByIdAssignedWords);

        this.wordRepo.save(word);
        this.userRepo.save(userById);
    }


    @Override
    public void assignTaskWithId(Long taskId, Long userId) {
        User currentUser = userService.findUserById(userId).orElse(null);
        Word wordById = wordRepo.findById(taskId).orElse(null);
        wordById.setAddedBy(currentUser);
        wordRepo.save(wordById);
        currentUser.getAddedWords().add(wordById);

        userRepo.save(currentUser);
    }

    @Override
    public void addTestWords() {
        User admin1 = userService.findUserById(Long.parseLong("1")).orElse(null);
        User test1 = userService.findUserById(Long.parseLong("2")).orElse(null);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Word word1Baustelle = new Word()
                .setTerm("die Sollbruchstelle")
                .setTranslation("predetermined breaking point")
                .setExample("Das Mittelloch der Feder ist eine Sollbruchstelle.")
                .setInputDate(LocalDate.parse("15-04-2012", dateTimeFormatter))
                .setLanguage(languageService.findLanguage(LanguageEnum.GERMAN));
        assignWordToUser(admin1, word1Baustelle);

        Word word2Meerschweinchen = new Word()
                .setTerm("das Meerschweinchen")
                .setTranslation("guinea pig")
                .setExample("Der Tag an dem das Meerschweinchen um die Welt flog")
                .setInputDate(LocalDate.parse("15-04-2012", dateTimeFormatter))
                .setLanguage(languageService.findLanguage(LanguageEnum.GERMAN));
        assignWordToUser(admin1, word2Meerschweinchen);

        Word word3Homme = new Word()
                .setTerm("Onirique")
                .setTranslation("dreamlike")
                .setExample("Onirique symbole de liberté pour les surréalistes, la cage figure dans nombre de leurs tableaux.")
                .setInputDate(LocalDate.parse("20-01-2022", dateTimeFormatter))
                .setLanguage(languageService.findLanguage(LanguageEnum.FRENCH));
        assignWordToUser(admin1, word3Homme);

        Word word4 = new Word()
                .setTerm("Dadivoso")
                .setTranslation("generous")
                .setExample("Seas dadivoso, pero capaz de recibir.")
                .setInputDate(LocalDate.parse("22-11-2008", dateTimeFormatter))
                .setLanguage(languageService.findLanguage(LanguageEnum.SPANISH));
        assignWordToUser(admin1, word4);

        Word word5 = new Word()
                .setTerm("Perenne")
                .setTranslation("everlasting")
                .setExample("Questa è la realtà, questo è il perenne principio della difesa dei diritti umani e delle libertà universali.")
                .setInputDate(LocalDate.parse("14-02-2011", dateTimeFormatter))
                .setLanguage(languageService.findLanguage(LanguageEnum.ITALIAN));
        assignWordToUser(test1, word5);

        userRepo.save(admin1);
        userRepo.save(test1);
    }

    private void assignWordToUser(User user, Word word) {
        Set<Word> assignedWords = user.getAddedWords();
        assignedWords.add(word);
        user.setAddedWords(assignedWords);

        word.setAddedBy(user);

        wordRepo.save(word);
    }

    @Override
    public void removeTaskById(Long taskId, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        Word word1 = user.getAddedWords().stream().filter(e -> e.getId().equals(taskId)).findFirst().orElse(null);
        boolean removed = user.getAddedWords().remove(word1);

        userRepo.save(user);
        wordRepo.delete(word1);
    }

    @Override
    public Set<Word> getAllUnassignedTasks() {
        return wordRepo.findAllByAddedByIsNull();

    }

    @Override
    public void returnTask(Long taskId, Long userId) {
        Word word = wordRepo.findById(taskId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);
        Word word1 = user.getAddedWords().stream().filter(e -> e.getId().equals(taskId)).findFirst().orElse(null);
        boolean remove = user.getAddedWords().remove(word1);
        word.setAddedBy(null);
        wordRepo.save(word);
        userRepo.save(user);
    }

    public Set<Word> getAllGermanWords() {
        Language languageGerman = languageRepo.findByName(LanguageEnum.GERMAN).orElse(null);
        Set<Word> allGermanWords = wordRepo.findAllByLanguage(languageGerman);

        return allGermanWords;
    }

    public Set<Word> getAllFrenchWords() {
        Language languageFrench = languageRepo.findByName(LanguageEnum.FRENCH).orElse(null);
        Set<Word> allWordsFrench = wordRepo.findAllByLanguage(languageFrench);

        return allWordsFrench;
    }

    public Set<Word> getAllSpanishWords() {
        Language languageSpanish = languageRepo.findByName(LanguageEnum.SPANISH).orElse(null);
        Set<Word> allWordsSpanish = wordRepo.findAllByLanguage(languageSpanish);

        return allWordsSpanish;
    }

    public Set<Word> getAllItalianWords() {
        Language languageItalian = languageRepo.findByName(LanguageEnum.ITALIAN).orElse(null);
        Set<Word> allWordsItalian = wordRepo.findAllByLanguage(languageItalian);

        return allWordsItalian;
    }

    public long getAllCount() {
        long count = wordRepo.count();
        return count;
    }

    public void removeWordById(Long id) {
        Word word = wordRepo.findById(id).orElse(null);
        User addedBy = word.getAddedBy();
        Set<Word> assignedWords = addedBy.getAddedWords();
        assignedWords.remove(word);

        userRepo.save(addedBy);
    }
}
