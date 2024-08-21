package Exemplo.Servicos;

import Exemplo.Annotations.GetFuncAnnotation;

@GetFuncAnnotation("getCodigo")
public class Produto {

    private Long codigo;

    public Produto(Long codigo){
        this.codigo = codigo;
    }

    public Long getCodigo(){
        return this.codigo;
    }

}
