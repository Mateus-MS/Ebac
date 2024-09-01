package main.java.entities.client;

import main.java.entities.z_generics.IGenericEntity;

public class Client implements IGenericEntity {

    private final String    name;
    private final String    cpf;
    private final int       idade;
    private final Character sexo;
    private final String    endereco;

    public Client(String name, String cpf, int idade, Character sexo, String endereco){
        this.name     = name;
        this.cpf      = cpf;
        this.idade    = idade;
        this.sexo     = sexo;
        this.endereco = endereco;
    }

    public String getName(){
        return this.name;
    }

    public String getCpf(){
        return this.cpf;
    }

    public int getIdade(){
        return this.idade;
    }

    public Character getSexo(){
        return this.sexo;
    }

    public String getEndereco(){
        return this.endereco;
    }

}
