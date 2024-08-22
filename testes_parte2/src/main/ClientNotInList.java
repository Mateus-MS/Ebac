package main;

public class ClientNotInList extends RuntimeException{

    public ClientNotInList(String erro){
        super(erro);
    }

}
