package com.lab1.lab.Web_Controller;

import com.lab1.lab.Model.Kategorija;
import com.lab1.lab.Model.Kniga;
import com.lab1.lab.Model.exceptions.BookIsAlreadyInShoppingCartException;
import com.lab1.lab.Service.kategorijaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.lab1.lab.Service.knigaService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/books")
public class Bookcontroller {

    final private knigaService knigaService;
    final private kategorijaService kategorijaService;

    public Bookcontroller(knigaService knigaService, kategorijaService kategorijaService) {
        this.knigaService = knigaService;
        this.kategorijaService = kategorijaService;
    }

    @GetMapping
    public String getKnigi(Model model) {
        List<Kniga> knigi = this.knigaService.findAll();
        model.addAttribute("knigi", knigi);
        return "books";
    }

    @GetMapping("/add-new")
    public String addNewBook(Model model) {
        List<Kategorija> categories = this.kategorijaService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("kniga", new Kniga());
        return "add-book";
    }


//    @GetMapping("/{id}/edit")
//    public String editBook(Model model, @PathVariable Long id) {
//        try {
//            Kniga kniga = this.knigaService.findById(id);
//            List<Kategorija> categories = this.kategorijaService.findAll();
//            model.addAttribute("kniga", kniga);
//            model.addAttribute("categories", categories);
//            return "add-book";
//        } catch ( RuntimeException ex ) {
//            return "redirect:/book?error=" + ex.getMessage();
//        }
//    }

    @PostMapping
    public String saveBook(
            @Valid Kniga kniga,
            BindingResult bindingResult,
            @RequestParam MultipartFile image,
            Model model) {
        if (bindingResult.hasErrors()) {
            List<Kategorija> categories = this.kategorijaService.findAll();
            model.addAttribute("categories", categories);
            return "add-book";
        }
        try {
            this.knigaService.saveKniga(kniga, image);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        try {
            this.knigaService.deleteById(id);
        } catch ( BookIsAlreadyInShoppingCartException ex ) {
            return String.format("redirect:/books?error=%s", ex.getMessage());
        }
        return "redirect:/books";

    }

}

