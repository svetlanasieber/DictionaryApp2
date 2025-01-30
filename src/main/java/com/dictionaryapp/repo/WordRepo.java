package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WordRepo extends JpaRepository<Word, Long> {

    Set<Word> findAllByAddedByIsNull();

    Set<Word> findAllByLanguage(Language language);
}
