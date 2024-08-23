package main.java.funcionario;

import main.java.generics.IGenericEntity;

import java.util.Arrays;
import java.util.List;


public class Funcionario implements IGenericEntity {

    private final String nome;
    private final Double register;
    private final int    idade;
    private final String cargo;
    private final int    salario;

    public Funcionario(String nome, Double register, int idade, String cargo, int salario){
        this.nome     = nome;
        this.register = register;
        this.idade    = idade;
        this.cargo    = cargo;
        this.salario  = salario;
    }

    public Double getRegister(){
        return this.register;
    }

    @Override
    public String toString(){
        return  "Nome       : " + this.nome + System.lineSeparator() +
                "Registro   : " + this.register + System.lineSeparator() +
                "Idade      : " + this.idade + System.lineSeparator() +
                "Cargo      : " + this.cargo + System.lineSeparator() +
                "Salario    : " + this.salario + System.lineSeparator();
    }

}
