package com.lab1.lab.Service;

import com.lab1.lab.Model.Kniga;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface knigaService {
    List<Kniga> findAll();
    Kniga saveKniga(Long id, String imeKniga, Integer brojPrimeroci, Long kategorijaId);
    Kniga saveKniga(Kniga kniga, MultipartFile image)throws IOException;
    Kniga updateProduct(Long id, Kniga kniga, MultipartFile image) throws IOException;
    void deleteById(Long id);
    Kniga findById(Long id);
    List<Kniga> findAllByName(String name);


}
