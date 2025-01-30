package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.impl.UserServiceImpl;
import com.dictionaryapp.service.impl.WordServiceImpl;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {

    private final LoggedUser loggedUser;
    private final WordServiceImpl wordService;
    private final UserServiceImpl userService;

    public HomeControllerImpl(LoggedUser loggedUser,
                              WordServiceImpl wordService,
                              UserServiceImpl userService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
        this.userService = userService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);

        Set<Word> allGermanWords = wordService.getAllGermanWords();
        Set<Word> allFrenchWords = wordService.getAllFrenchWords();
        Set<Word> allSpanishWords = wordService.getAllSpanishWords();
        Set<Word> allItalianWords = wordService.getAllItalianWords();

        model.addAttribute("allGermanWords", allGermanWords);
        model.addAttribute("allFrenchWords", allFrenchWords);
        model.addAttribute("allSpanishWord", allSpanishWords);
        model.addAttribute("allItalianWords", allItalianWords);

        long allCount = wordService.getAllCount();
        model.addAttribute("allCount", allCount);

        return "home";
    }

    @Override
    public String removeAll(Model model) {
        userService.removeAllWords();
        return "redirect:/home";
    }

    @Override
    public String removeById(Long id) {
        wordService.removeWordById(id);

        return "redirect:/home";
    }


}
