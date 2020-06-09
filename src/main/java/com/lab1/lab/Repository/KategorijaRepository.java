package com.lab1.lab.Repository;

import com.lab1.lab.Model.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {
//    Optional<Kategorija> findByid(Long id);
//    List<Kategorija> findAll();
//    Kategorija save(Kategorija kategorija);
//    void deleteById(long id);
}
