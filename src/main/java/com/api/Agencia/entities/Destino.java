package com.api.Agencia.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String nome;
    private String localidade;
    private String detalhes;
    private double nota;
    private double contaNota;

    public Destino() {

    }
}
