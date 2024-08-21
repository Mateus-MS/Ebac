package Exemplo;

import Exemplo.Servicos.Cliente;
import Exemplo.Servicos.Produto;

import static Exemplo.GetCode.getCode;

public class Demo {

    public static void main(String[] args){

        Cliente cliente = new Cliente("123.123.123-45");
        Produto produto = new Produto(9424150519636L);

        System.out.println(getCode(cliente).toString());
        System.out.println(getCode(produto).toString());

        //System.out.println(getCode(3).toString()); - Throws SemAnotacaoException

    }

}
