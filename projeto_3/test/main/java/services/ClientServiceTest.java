package main.java.services;

import main.java.client.ClientDAO;
import main.java.client.ClientService;
import org.junit.Assert;
import org.junit.Test;

import main.java.client.Client;

public class ClientServiceTest {

    @Test
    public void test(){
        Client client = new Client(
            "Mateus Alves",
            "12312312312",
            21,
            'M',
            "R. Prof."
        );

        ClientDAO     dao     = ClientDAO.getInstance();
        ClientService service = new ClientService(dao);

        //Se o cliente for registrado com sucesso
        //o metodo register retorna true caso contrário
        //retorna false
        Assert.assertTrue(service.register(client));

        //Testa se o metodo search funciona
        Assert.assertEquals(client.getCpf(), service.search(client.getCpf()).getCpf());

        //Limpa do dado inserido com o teste
        Assert.assertTrue(service.delete(client.getCpf()));
    }

}
