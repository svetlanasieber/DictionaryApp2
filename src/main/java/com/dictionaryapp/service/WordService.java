package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Word;

import java.util.Set;

public interface WordService {

    void addWord(AddWordDTO addWordDTO, Long id) ;

    void assignTaskWithId(Long taskId, Long userId);

    void addTestWords();

    void removeTaskById(Long taskId, Long userId);

    Set<Word> getAllUnassignedTasks();

    void returnTask(Long taskId, Long userId);
}
