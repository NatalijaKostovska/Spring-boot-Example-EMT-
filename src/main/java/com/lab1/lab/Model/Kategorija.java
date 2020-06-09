package com.lab1.lab.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Kategorija {
    @Id //oznachuva deka ova pole e primaren kluch na ovoj entitet vo tabelata
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String opis;
    @JsonIgnore
    @OneToMany(
            mappedBy = "kategorija"
//            orphanRemoval = true   -> this will remove all related entities, if we remove given manufacturer it will remove also all products
    )
    List<Kniga> knigaList;

}
