package com.teuz.domain.marca.model;

import java.util.List;
import java.util.UUID;

import com.teuz.domain.carro.model.Carro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_marca")
public class Marca {

    @Id
    @GeneratedValue
    @Column(columnDefinition="uuid", nullable=false, updatable=false, unique=true)
    private UUID id;
    
    @Column(name="name", nullable=false, updatable=false, unique=true, length=20)
    private String name;

    @OneToMany(mappedBy="marca", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Carro> carros;

    public Marca(){}
    public Marca(String name){
        this.name = name;
    }

    public UUID getId(){return this.id;}
    
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    public void setCarros(List<Carro> carros){this.carros = carros;}
    public List<Carro> getCarros(){return this.carros;}

}
