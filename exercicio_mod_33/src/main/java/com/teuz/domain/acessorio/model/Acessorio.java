package com.teuz.domain.acessorio.model;

import java.util.List;
import java.util.UUID;

import com.teuz.domain.carro.model.Carro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_acessorio")
public class Acessorio {

    @Id
    @GeneratedValue
    @Column(columnDefinition="uuid", nullable=false, updatable=false, unique=true)
    private UUID id;

    @Column(name="nome", nullable=false, updatable=false)
    private String name;

    @ManyToMany(mappedBy="acessorios")
    private List<Carro> carros;

    public Acessorio(){}
    public Acessorio(String name){
        this.name = name;
    }

    public UUID getId(){return this.id;}
    
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    public void setCarros(List<Carro> carros){this.carros = carros;}
    public List<Carro> getCarros(){return this.carros;}

}
