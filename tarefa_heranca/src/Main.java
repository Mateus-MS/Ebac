public class Main {
    public static void main(String[] args) {
        PessoaFisica pessoaFisica = new PessoaFisica("Mateus", "Programador","123.123.123-12");
        pessoaFisica.cumprimenta();

        PessoaJuridica pessoaJuridica = new PessoaJuridica("Khelbia", "Tesla","12.1234.123/1234-12");
        pessoaJuridica.cumprimenta();
    }
}