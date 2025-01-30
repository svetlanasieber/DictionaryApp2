package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.service.impl.WordServiceImpl;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordControllerImpl implements WordController {

    private final LoggedUser loggedUser;
    private final WordServiceImpl wordService;

    public WordControllerImpl(LoggedUser loggedUser, WordServiceImpl wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @Override
    public String addWord() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "word-add";
    }

    @Override
    public String addWord(AddWordDTO addWordDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addWordDTO", addWordDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addWordDTO", result);
            return "redirect:/words/add-word";
        }

        this.wordService.addWord(addWordDTO,loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String assignTask(Long id) {
        wordService.assignTaskWithId(id, loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String removeTask(Long id) {
        wordService.removeTaskById(id,loggedUser.getId());

        return "redirect:/home";
    }

    @Override
    public String returnTask(Long id) {
        wordService.returnTask(id, loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public AddWordDTO addWordDTO() {
        return new AddWordDTO();
    }
}
