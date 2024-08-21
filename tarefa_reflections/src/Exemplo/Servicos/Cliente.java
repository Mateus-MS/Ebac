package Exemplo.Servicos;

import Exemplo.Annotations.GetFuncAnnotation;

@GetFuncAnnotation("getCpf")
public class Cliente {

    private final String cpf;

    public Cliente(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return this.cpf;
    }

}
