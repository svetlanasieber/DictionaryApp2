package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity {

    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageEnum name;

    @Column
    private String description;

    @OneToMany(mappedBy = "language")
    private Set<Word> words;

    public Language() {
    }

    public LanguageEnum getName() {
        return name;
    }

    public Language setName(LanguageEnum moodName) {
        this.name = moodName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Language setDescription(String description) {
        this.description = description;
        return this;
    }


    public Set<Word> getOffers() {
        return words;
    }

    public Language setOffers(Set<Word> words) {
        this.words = words;
        return this;
    }
}
