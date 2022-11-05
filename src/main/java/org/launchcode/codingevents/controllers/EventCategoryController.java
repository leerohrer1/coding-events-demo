package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Scanner;

public class EventCategoryController {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());

        return "eventCategories/index";
    }

    @GetMapping
    public String renderCreateEventCategoryForm(Model model, EventCategory eventCategory) {
        model.addAttribute("title", "Create Category");
        model.addAttribute("eventCategory", eventCategoryRepository.save(eventCategory));

        return "eventCategories/create";
    }

    @PostMapping
    public String processCreateEventCategoryForm(Model model, @Valid @ModelAttribute EventCategory eventCategory, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute(new EventCategory());
            return "eventCategories/create";
        }

        eventCategoryRepository.save(eventCategory);
        return "redirect:";
    }
}
