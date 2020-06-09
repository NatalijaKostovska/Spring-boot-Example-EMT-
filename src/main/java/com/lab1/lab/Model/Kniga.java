package com.lab1.lab.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Kniga {
    @Id //oznachuva deka ova pole e primaren kluch na ovoj entitet vo tabelata
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String imeKniga;
    @Min(value = 1, message = "Brojot na primeroci ne moze da bide pomal od 1")
    @NotNull(message = "Brojot na primeroci ne moze da bide prazen")
    private Integer brojPrimeroci;
    @NotNull
    @ManyToOne
    private Kategorija kategorija;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private List<ShoppingCart> shoppingCarts;

    @Column(name = "image")
    @Lob
    private String imageBase64;
    private float price;

    public Kniga(Long id, String imeKniga, Integer brojPrimeroci, Kategorija kategorija) {
        this.id = id;
        this.imeKniga = imeKniga;
        this.brojPrimeroci = brojPrimeroci;
        this.kategorija = kategorija;
    }

}
