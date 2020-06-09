//package com.lab1.lab.Repository.impl;
//
//import com.lab1.lab.Model.Kategorija;
//import com.lab1.lab.Repository.KategorijaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InmemoryKategorijaRepositoryimpl implements KategorijaRepository {
//    private HashMap<Long, Kategorija> kategorii;
//    private Long counter;
//
//
//    public InmemoryKategorijaRepositoryimpl() {
//        this.kategorii = new HashMap<>();
//        Kategorija kategorija1 = new Kategorija(1L,"History",null);
//
//        Kategorija kategorija2 = new Kategorija(2L,"Business","Ethnic & Cultural\n" +
//                "Europe\n" +
//                "Historical\n" +
//                "Leaders & Notable People\n" +
//                "Military");
//        Kategorija kategorija3 = new Kategorija(3L,"Comics","Ethnic & Cultural\n" +
//                "Europe\n" +
//                "Historical\n" +
//                "Leaders & Notable People\n" +
//                "Military");
//        Kategorija kategorija4 = new Kategorija(4L,"Biographies","ffff");
//        Kategorija kategorija5 = new Kategorija(5L,"Fantasy","ddddd");
//        this.kategorii.put(kategorija1.getId(),kategorija1);
//        this.kategorii.put(kategorija2.getId(),kategorija2);
//        this.kategorii.put(kategorija3.getId(),kategorija3);
//        this.kategorii.put(kategorija4.getId(),kategorija4);
//        this.kategorii.put(kategorija5.getId(),kategorija5);
//
//    }
//
//    @Override
//    public Optional<Kategorija> findByid(Long id) {
//        return Optional.ofNullable(this.kategorii.get(id));
//    }
//
//    @Override
//    public List<Kategorija> findAll() {
//        return new ArrayList<>(this.kategorii.values());
//    }
//
//    @Override
//    public Kategorija save(Kategorija kategorija) {
//        if (kategorija.getId() == null){
//            kategorija.setId(kategorija.getId());
//        }
//        this.kategorii.put(kategorija.getId(),kategorija);
//        return kategorija;
//    }
//    @Override
//    public void deleteById(long id) {
//        this.kategorii.remove(id);
//    }
//
//}
