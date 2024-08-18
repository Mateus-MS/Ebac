public class PessoaFisica extends Pessoa{

    private String emprego;
    private String cpf;

    public PessoaFisica(String _nome, String _emprego, String _cpf){
        super(_nome);

        this.emprego = _emprego;
        this.cpf = _cpf;
    }

}
