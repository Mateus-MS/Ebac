public class Pessoa implements Comparable<Pessoa>{

    private final String nome;
    private final int idade;
    private final Character sexo;

    public Pessoa(String nome, int idade, Character sexo){
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public Character getSexo(){
        return this.sexo;
    }

    @Override
    public int compareTo(Pessoa other){
        return this.nome.compareTo(other.nome);
    }

    @Override
    public String toString(){

        StringBuilder text = new StringBuilder();
        text.append("Nome: ");
        text.append(this.nome).append(". ");
        text.append("Idade: ");
        text.append(this.idade).append(". ");
        text.append("Sexo: ");
        text.append(this.sexo).append(".");

        return text.toString();

    }

}