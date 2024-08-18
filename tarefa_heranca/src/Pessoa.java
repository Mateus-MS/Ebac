public abstract class Pessoa {

    public String nome;

    public Pessoa(String _nome){
        this.nome = _nome;
    }

    public void cumprimenta(){
        System.out.printf("Me chamo %s.", this.nome);
    }

}
