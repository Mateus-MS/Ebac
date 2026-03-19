package com.teuz.domain.carro.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Check;

import com.teuz.domain.acessorio.model.Acessorio;
import com.teuz.domain.marca.model.Marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="tb_carro")
@Check(constraints="ano > 1900 AND ano < 2025")
public class Carro {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition="uuid", nullable=false, updatable=false, unique=true)
    private UUID id;

    @Column(name="modelo", nullable=false, updatable=false)
    @Enumerated(EnumType.STRING)
    private Modelo modelo;

    @Column(name="ano", nullable=false, updatable=false)
    @Min(1901)
    @Max(2025)
    private Integer ano;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="marca_id", nullable=false, updatable=false)
    private Marca marca;

    @ManyToMany
    @JoinTable(
        name = "tb_carro_acessorio",
        joinColumns = @JoinColumn(name = "carro_id"),
        inverseJoinColumns = @JoinColumn(name = "acessorio_id")
    )
    private List<Acessorio> acessorios;

    public Carro(){}
    public Carro(Modelo modelo, Integer ano, Marca marca){
        this.ano = ano;
        this.modelo = modelo;
        this.marca = marca;
    }

    public UUID getId(){return this.id;}

    public void setModelo(Modelo modelo){this.modelo = modelo;}
    public Modelo getModelo(){return this.modelo;}

    public void setAno(Integer ano){this.ano = ano;}
    public Integer getAno(){return this.ano;}

    public void setMarca(Marca marca){this.marca = marca;}
    public Marca getMarca(){return this.marca;}

    public void setAcessorios(List<Acessorio> acessorios){this.acessorios = acessorios;}
    public List<Acessorio> getAcessorios(){return this.acessorios;}

}
