package main;

public class ClientDAO implements IClientDAO{

    @Override
    public boolean salvar(String client){
        return false;
    }

    @Override
    public boolean remove(String client){
        return false;
    }

    @Override
    public boolean atualiza(String oldClient, String newClient){
        return false;
    }

}
