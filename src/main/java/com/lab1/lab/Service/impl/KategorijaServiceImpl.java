package com.lab1.lab.Service.impl;

import com.lab1.lab.Model.Kategorija;
import com.lab1.lab.Model.exceptions.CategoryExceptionNotFound;
import com.lab1.lab.Repository.KategorijaRepository;
import com.lab1.lab.Service.kategorijaService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class KategorijaServiceImpl implements kategorijaService {
    private final KategorijaRepository kategorijaRepository;

    public KategorijaServiceImpl(KategorijaRepository kategorijaRepository) {
        this.kategorijaRepository = kategorijaRepository;
    }

    @Override
    public List<Kategorija> findAll() {
        return this.kategorijaRepository.findAll();
    }

    @Override
    public Kategorija findById(Long id) {
        return this.kategorijaRepository.findById(id).orElseThrow(()-> new CategoryExceptionNotFound(id));
    }

    @Override
    public List<Kategorija> findByName(String name) {
        return this.kategorijaRepository.findAll();
    }

    @Override
    public Kategorija save(@RequestBody Kategorija kategorija) {
        return this.kategorijaRepository.save(kategorija);
    }

    @Override
    public Kategorija update(Long id, Kategorija kategorija) {
        Kategorija k = this.findById(id);
        k.setIme(kategorija.getIme());
        k.setOpis(kategorija.getOpis());
        return this.kategorijaRepository.save(k);
    }

    @Override
    public void deleteById(Long id) {
        this.kategorijaRepository.deleteById(id);
    }

    @Override
    public Kategorija updateName(Long id, String name) {
        Kategorija c = this.findById(id);
        c.setIme(name);
        return this.kategorijaRepository.save(c);
    }

}
