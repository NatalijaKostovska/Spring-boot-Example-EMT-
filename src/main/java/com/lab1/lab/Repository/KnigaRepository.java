package com.lab1.lab.Repository;

import com.lab1.lab.Model.Kategorija;
import com.lab1.lab.Model.Kniga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface KnigaRepository  extends JpaRepository<Kniga, Long> {
//    Optional<Kniga> findByid(Long id);
//    List<Kniga> findAll();
//    Kniga save(Kniga kniga);
//    void deleteById(long id);
    List<Kniga> findAllByOrderByPriceAsc();
    List<Kniga> findAllByOrderByPriceDesc();
    //    @Query("select count(p) from Product p where p.price > :price")
    long countAllByPriceGreaterThan(@Param("price") Float price);
    List<Kniga> findAllByPriceGreaterThan(@Param("price") Float price);
    //    @Query(value = "select * from products join manufacturers m where m.id = :id ;", nativeQuery = true)
    //    @Query("select p from Product p where p.manufacturer.id = :id")
    List<Kniga> findAllByKategorijaId(@Param("id") Long id);
//    List<Kniga> findAllByNameLikeAndKategorijaIdOrderByPriceDesc(String name, Long manufacturerId);

}
