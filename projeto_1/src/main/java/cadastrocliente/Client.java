package cadastrocliente;
public class Client {
    
    private final String nome;
    private final String cpf;
    private final String telefone;
    private final String endereco;
    private final String estado;
    private final String cidade;
    
    public Client(String nome, String cpf, String telefone, String endereco, String estado, String cidade){
        this.nome = nome;
        this.cpf  = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.estado = estado;
        this.cidade = cidade;
    }
    
    public boolean equalsTo(Client client){
        return this.nome.equals(client.getNome())         &&
               this.telefone.equals(client.getTelefone()) &&
               this.endereco.equals(client.getEndereco()) &&
               this.estado.equals(client.getEstado())     &&
               this.cidade.equals(client.getCidade());
    }
    
    public String getCPF(){
        return this.cpf;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getTelefone(){
        return this.telefone;
    }
    
    public String getEndereco(){
        return this.endereco;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public String getCidade(){
        return this.cidade;
    }
    
}
