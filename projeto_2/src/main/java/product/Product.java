package main.java.product;

import main.java.annotations.Getter;
import main.java.generics.IGenericEntity;

@Getter("getCodigo")
public class Product implements IGenericEntity {

    private final String  nome;
    private final double  codigo;
    private final float   preco;
    private final boolean available;

    public Product(String nome, double codigo, float preco, boolean available){
        this.nome      = nome;
        this.codigo    = codigo;
        this.preco     = preco;
        this.available = available;
    }

    public double getCodigo(){
        return this.codigo;
    }

    @Override
    public String toString(){
        String estoque = this.available ? "Sim" : "Não";

        return  "Nome       : " + this.nome + System.lineSeparator() +
                "Codigo     : " + this.codigo + System.lineSeparator() +
                "Preço      : " + this.preco + System.lineSeparator() +
                "Estoque    : " + estoque + System.lineSeparator();
    }

}
