package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/words")
public interface WordController {

    @GetMapping("/add-word")
    String addWord();

    @PostMapping("/add-word")
    String addWord(@Valid AddWordDTO addWordDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/assign-task/{id}")
    String assignTask(@PathVariable Long id);

    @GetMapping("/remove/{id}")
    String removeTask(@PathVariable Long id);

    @GetMapping("/return/{id}")
    String returnTask(@PathVariable Long id);
}

