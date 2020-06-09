package com.lab1.lab.Service;

import com.lab1.lab.Model.Kategorija;

import java.util.List;

public interface kategorijaService {
    List<Kategorija> findAll();
    Kategorija findById(Long id);
    List<Kategorija> findByName(String name);
    Kategorija save(Kategorija kategorija);
    Kategorija updateName(Long id, String name);
    Kategorija update(Long id, Kategorija kategorija);
    void deleteById(Long id);

}
