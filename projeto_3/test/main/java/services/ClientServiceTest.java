package main.java.services;

import main.java.entities.client.ClientDAO;
import main.java.entities.z_generics.GenericService;
import org.junit.Assert;
import org.junit.Test;

import main.java.entities.client.Client;

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
        GenericService<Client> service = new GenericService<>(dao);

        //Se o cliente for registrado com sucesso
        //o metodo register retorna true caso contrário
        //retorna false
        Assert.assertTrue(service.register(client));

        //Testa se o metodo atualiza funciona
        Client newClient = new Client(
            "Novo Mateus",
            "12312312312",
            21,
            'M',
            "R. Prof."
        );
        Assert.assertTrue(service.update(newClient));
        Assert.assertEquals(service.search(newClient.getCpf()).getName(), newClient.getName());

        //Limpa do dado inserido com o teste
        Assert.assertTrue(service.delete(client.getCpf()));
    }

}
