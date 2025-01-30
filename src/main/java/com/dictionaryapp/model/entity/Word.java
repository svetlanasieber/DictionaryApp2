package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "words")
public class Word extends BaseEntity {

    @Column
    private String term;

    @Column
    private String translation;

    @Column(columnDefinition = "TEXT")
    private String example;


    @ManyToOne(fetch = FetchType.EAGER)
    private Language language;

    @Column
    private LocalDate inputDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User addedBy;


    public Word() {
    }

    public String getExample() {
        return example;
    }

    public Word setExample(String content) {
        this.example = content;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Word setLanguage(Language language) {
        this.language = language;
        return this;
    }



    public LocalDate getInputDate() {
        return inputDate;
    }

    public Word setInputDate(LocalDate dueDate) {
        this.inputDate = dueDate;
        return this;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public Word setAddedBy(User assignedTo) {
        this.addedBy = assignedTo;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public Word setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public Word setTerm(String name) {
        this.term = name;
        return this;
    }
}
