package main.java.client;

import java.util.Locale;

public class Client {

    private String    name;
    private String    cpf;
    private int       idade;
    private Character sexo;
    private String    endereco;

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
