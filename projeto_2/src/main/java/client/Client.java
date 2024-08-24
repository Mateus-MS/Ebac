package main.java.client;

import main.java.annotations.Getter;
import main.java.generics.IGenericEntity;

/**
 *  Objeto cliente que guarda funções
 *  e informações de um unico client
 */
@Getter("getCpf")
public class Client implements IGenericEntity {

    private final String    nome;
    private final int       idade;
    private final String    endereco;
    private final Character sexo;
    private final Double     cpf;

    public Client(String nome, int idade, String endereco, Character sexo, Double cpf){
        this.nome     = nome;
        this.idade    = idade;
        this.endereco = endereco;
        this.sexo     = sexo;
        this.cpf      = cpf;
    }

    public Double getCpf(){
        return this.cpf;
    }

    @Override
    public String toString(){
        return  "Nome    : " + this.nome + System.lineSeparator() +
                "Idade   : " + this.idade + System.lineSeparator() +
                "Endereco: " + this.endereco + System.lineSeparator() +
                "Sexo    : " + this.sexo + System.lineSeparator() +
                "Cpf     : " + this.cpf + System.lineSeparator();
    }

}
