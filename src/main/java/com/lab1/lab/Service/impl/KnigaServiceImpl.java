package com.lab1.lab.Service.impl;

import com.lab1.lab.Model.Kategorija;
import com.lab1.lab.Model.Kniga;
import com.lab1.lab.Model.exceptions.BookExceptionNotFound;
import com.lab1.lab.Model.exceptions.BookIsAlreadyInShoppingCartException;
import com.lab1.lab.Repository.KnigaRepository;
import com.lab1.lab.Service.kategorijaService;
import com.lab1.lab.Service.knigaService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
@Service
public class KnigaServiceImpl implements knigaService {



    private final KnigaRepository knigaRepository;
    private final kategorijaService kategorijaService;
    public KnigaServiceImpl(KnigaRepository knigaRepository, kategorijaService kategorijaService) {
        this.knigaRepository = knigaRepository;
        this.kategorijaService = kategorijaService;
    }

    @Override
    public List<Kniga> findAll() {
        return this.knigaRepository.findAll();
    }



    @Override
    public Kniga saveKniga(Kniga kniga, MultipartFile image) throws IOException {
        Kategorija kategorija = this.kategorijaService.findById(kniga.getKategorija().getId());
        kniga.setKategorija(kategorija);
        if (image != null || !image.getName().isEmpty()){
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s",
                    image.getContentType(),
                    Base64.getEncoder().encodeToString(bytes));
            kniga.setImageBase64(base64Image);
        }
        return this.knigaRepository.save(kniga);
    }
    @Override
    public Kniga saveKniga(Long id, String imeKniga, Integer brojPrimeroci, Long kategorijaId) {
        Kategorija k = this.kategorijaService.findById(kategorijaId);
        Kniga kniga = new Kniga(id,imeKniga,brojPrimeroci,k);
        return this.knigaRepository.save(kniga);

    }

    @Override
    public Kniga updateProduct(Long id, Kniga kniga, MultipartFile image) throws IOException {
        Kniga k = this.findById(id);
        Kategorija c = this.kategorijaService.findById(k.getKategorija().getId());
        k.setKategorija(c);
        k.setId(kniga.getId());
        k.setBrojPrimeroci(kniga.getBrojPrimeroci());
        k.setImeKniga(kniga.getImeKniga());
        if(image!=null && !image.getName().isEmpty()){
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s",image.getContentType(),
                    Base64.getEncoder().encodeToString(bytes));
            k.setImageBase64(base64Image);
        }
        return this.knigaRepository.save(k);
    }


    @Override
    public void deleteById(Long id) {
        Kniga kniga = this.findById(id);
        if (kniga.getShoppingCarts().size() > 0) {
            //avoid deleting product that is already in shopping cart!
            throw new BookIsAlreadyInShoppingCartException(kniga.getImeKniga());
        }

        this.knigaRepository.deleteById(id);
    }

    @Override
    public Kniga findById(Long id) {
        return this.knigaRepository.findById(id).orElseThrow(()->new BookExceptionNotFound(id));
    }

    @Override
    public List<Kniga> findAllByName(String name) {
        return null;
    }
}
