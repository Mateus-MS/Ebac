package main;

import java.util.ArrayList;
import java.util.List;

public class ClientDAOMock implements IClientDAO{

    private final List<String> clients = new ArrayList<>();

    @Override
    public boolean salvar(String client){
        return this.clients.add(client);
    }

    @Override
    public boolean remove(String client){
        return this.clients.remove(client);
    }

    @Override
    public boolean atualiza(String oldClient, String newClient){
        if(!this.clients.remove(oldClient)){
            throw new ClientNotInList("Tentando atualizar um cliente que não está na lista...");
        }
        this.clients.add(newClient);
        return true;
    }

}
