package main;

import org.junit.Assert;
import org.junit.Test;

public class ClientServiceTest {

    @Test
    public void testaCriarService(){

        IClientDAO dao = new ClientDAO();
        ClientService cli = new ClientService(dao);
        Assert.assertNotNull(cli);

    }

    @Test
    public void testaSalvarCliente(){

        IClientDAO dao = new ClientDAOMock();
        ClientService cli = new ClientService(dao);
        Assert.assertTrue(cli.salvar("Test"));

    }

    @Test
    public void testaBuscarCliente(){

        IClientDAO dao = new ClientDAOMock();
        ClientService cli = new ClientService(dao);
        cli.salvar("Test");
        Assert.assertTrue(cli.remove("Test"));

    }

    @Test(expected = ClientNotInList.class)
    public void testaAtualizaClientNotInList(){

        String oldClient = "Test1";
        String newClient = "Test2";

        IClientDAO dao = new ClientDAOMock();
        ClientService cli = new ClientService(dao);

        Assert.assertFalse(cli.atualiza(oldClient, newClient));

    }

    @Test
    public void testaAtualizaClientInList(){

        String oldClient = "Test1";
        String newClient = "Test2";

        IClientDAO dao = new ClientDAOMock();
        ClientService cli = new ClientService(dao);

        cli.salvar("Test1");

        Assert.assertTrue(cli.atualiza(oldClient, newClient));

    }

}
