public class PessoaJuridica extends Pessoa{

    private String nomeDaEmpresa;
    private String cnpj;

    public PessoaJuridica(String _nome, String _nomeDaEmpresa,String _cnpj){
        super(_nome);

        this.nomeDaEmpresa = _nomeDaEmpresa;
        this.cnpj = _cnpj;
    }

}
