package com.dictionaryapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "/")
public interface HomeController {

    @GetMapping
    String index();

    @GetMapping("/home")
    String home(Model model);

    @GetMapping("/home/remove-all")
    String removeAll(Model model);

    @GetMapping("/home/remove-word-by-id/{id}")
    String removeById(@PathVariable("id")Long id);

}
