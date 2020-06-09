package com.lab1.lab.Web_Controller;

import com.lab1.lab.Model.Kategorija;
import com.lab1.lab.Model.ShoppingCart;
import com.lab1.lab.Service.AuthService;
import com.lab1.lab.Service.ShoppingCartService;
import com.lab1.lab.Service.kategorijaService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/categories")
public class CategoryControllerRest {
    private final ShoppingCartService shoppingCartService;
    final private kategorijaService kategorijaService;
    private final AuthService authService;

    public CategoryControllerRest(ShoppingCartService shoppingCartService, kategorijaService kategorijaService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.kategorijaService = kategorijaService;
        this.authService = authService;
    }
//    @GetMapping
//    public List<ShoppingCart> findAllByUsername() {
//        return this.shoppingCartService.findAllByUsername(this.authService.getCurrentUserId());
//    }


    @GetMapping
    public List<Kategorija> findAll(@RequestParam(required = false ) String name){
        if(name==null){
            return this.kategorijaService.findAll();
        }else {
            return this.kategorijaService.findByName(name);
        }
    }
    @GetMapping("by-name")
    public List<Kategorija>findByName(@RequestParam String name){
        return kategorijaService.findByName(name);
    }


    @GetMapping("/{id}")
    public Kategorija findById(@PathVariable Long id){
        return this.kategorijaService.findById(id);
    }


//    @PostMapping
//    public Manufacturer save(@RequestParam String name,@RequestParam String adress){
//        Manufacturer manufacturer = new Manufacturer();
//        manufacturer.setName(name);
//        manufacturer.setAdress(adress);
//        return this.manufacturerService.save(manufacturer);
//    }

    @PostMapping
    public Kategorija save(@RequestBody @Valid Kategorija manufacturer){
        return this.kategorijaService.save(manufacturer);
    }


    @PutMapping("/{id}")
    public Kategorija update(@PathVariable Long id, @Valid Kategorija manufacturer){
        return this.kategorijaService.update(id,manufacturer);

    }


    @PatchMapping("/{id}") //koga updatirame del od resursot koristime patch
    public Kategorija updateName(@PathVariable Long id, @RequestParam String name){
        return this.kategorijaService.updateName(id,name);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.kategorijaService.deleteById(id);
    }
}

