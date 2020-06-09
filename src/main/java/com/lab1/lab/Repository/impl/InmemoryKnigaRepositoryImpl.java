//package com.lab1.lab.Repository.impl;
//
//import com.lab1.lab.Model.Kniga;
//import com.lab1.lab.Repository.KnigaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.PostConstruct;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//@Repository
//public class InmemoryKnigaRepositoryImpl implements KnigaRepository {
//    private HashMap<Long,Kniga> knigi;
//    private Long counter;
//
//    @PostConstruct
//    public void init(){
//        this.counter=0L;
//        this.knigi = new HashMap<>();
//        Long id = this.generateKey();
//        this.knigi.put(id,new Kniga(id,"Volshebnoto samarche",5,null,null));
//        id = this.generateKey();
//        this.knigi.put(id,new Kniga(id,"Srce",10,null,null));
//    }
//
//    @Override
//    public Optional<Kniga> findByid(Long id) {
//        return Optional.ofNullable(this.knigi.get(id));
//    }
//
//    @Override
//    public List<Kniga> findAll() {
//        return new ArrayList<>(this.knigi.values());
//    }
//
//    @Override
//    public Kniga save(Kniga kniga) {
//        if(kniga.getId()==null){
//            kniga.setId(this.generateKey());
//        }
//        this.knigi.put(kniga.getId(),kniga);
//        return kniga;
//    }
//
//
//    @Override
//    public void deleteById(long id) {
//        this.knigi.remove(id);
//    }
//
//    public Long generateKey(){
//        return counter++;
//    }
//}
