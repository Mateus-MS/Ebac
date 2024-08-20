package cadastrocliente;

import java.util.HashMap;
import java.util.Map;

public class ClientMapDAO implements IClientDAO {
    
    private final Map<String, Client> map;
    
    public ClientMapDAO(){
        this.map = new HashMap<>();
    }
    
    @Override
    public boolean cadastrar(Client client){
        String cpf = client.getCPF();
        
        if(!this.map.containsKey(cpf)){
            this.map.put(cpf, client);
            return true;
        }
        return false;
    }
    
    @Override
    public Client getClient(String cpf){
        return this.map.get(cpf);
    };
    
    @Override
    public void attClient(Client client){
        this.map.replace(client.getCPF(), client);
    }

    @Override
    public void removeClient(String cpf){
        this.map.remove(cpf);
    }
    
    
}
