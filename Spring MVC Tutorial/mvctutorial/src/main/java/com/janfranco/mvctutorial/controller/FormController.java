package com.janfranco.mvctutorial.controller;

import javax.validation.Valid;

import com.janfranco.mvctutorial.model.Student;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form")
public class FormController {

    /*
     * @RequestMapping("/show-form") public String showForm() { return "form"; }
     * 
     * @RequestMapping("/process-form") public String
     * processForm(@RequestParam("name") String name, Model model) {
     * model.addAttribute("message", name); return "process-form"; }
     */

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/show-form")
    public String showForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "form";
    }

    @RequestMapping("/process-form")
    public String processForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "process-form";
    }
}
