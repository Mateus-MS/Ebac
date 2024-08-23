package main.test.client;

import main.java.client.Client;
import main.java.client.DAO.ClientDAOMock;
import main.java.client.service.ClientService;
import main.java.exceptions.DataAlreadyInDBException;
import main.java.exceptions.DataNotPresentInDBException;
import main.java.generics.DAO.IGenericDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientServiceTest {

    private ClientService cli;

    @Before
    public void testInit(){
        IGenericDAO<Client> dao = ClientDAOMock.getInstance();
        Assert.assertNotNull(dao);

        this.cli = new ClientService(dao);
        Assert.assertNotNull(this.cli);
    }

    @Test
    public void testRegisterClient(){
        Client test = new Client(
                "Test",
                18,
                "R. Prof. Santo Antônio n99",
                'M',
                03335566238D);

        Assert.assertTrue(this.cli.register(test));
    }

    @Test(expected = DataAlreadyInDBException.class)
    public void testRegisterClientDuplicated(){
        Client test = new Client(
                "Testa Duplicado",
                18,
                "R. Prof. Santo Antônio n99",
                'M',
                123123D);

        this.cli.register(test);
        this.cli.register(test);
    }

    @Test
    public void testPesquisa(){
        Client test = new Client(
                "Testa Pesquisa",
                18,
                "R. Prof. Santo Antônio n99",
                'M',
                11111111D);

        this.cli.register(test);
        Client result = this.cli.pesquisa(test.getCpf());
        Assert.assertEquals(test, result);
    }

    @Test
    public void testPesquisaNull(){
        Client test = new Client(
                "Testa Pesquisa",
                18,
                "R. Prof. Santo Antônio n99",
                'M',
                111111113333D);

        Assert.assertNull(this.cli.pesquisa(test.getCpf()));
    }

    @Test
    public void testAtualiza(){
        Client oldClient = new Client(
                "Velho",
                18,
                "R. Prof. Santo Antônio n99",
                'M',
                11117113333D);

        this.cli.register(oldClient);

        Client newClient = new Client(
                "Novo",
                21,
                "R. Atualizada n2",
                'F',
                oldClient.getCpf()
        );

        Assert.assertTrue(this.cli.atualiza(newClient));
        Assert.assertEquals(this.cli.pesquisa(newClient.getCpf()), newClient);
    }

    @Test(expected = DataNotPresentInDBException.class)
    public void testAtualizaNull(){
        Client newClient = new Client(
                "Atualizado",
                21,
                "R. Atualizada n2",
                'F',
                1231231412D
        );

        Assert.assertTrue(this.cli.atualiza(newClient));
    }

    @Test
    public void testExcluir(){
        Client test = new Client(
                "Atualizado",
                21,
                "R. Atualizada n2",
                'F',
                124124D
        );

        this.cli.register(test);
        Assert.assertTrue(this.cli.excluir(test.getCpf()));
    }

    @Test(expected = DataNotPresentInDBException.class)
    public void testExcluirNull(){
        Assert.assertFalse(this.cli.excluir(123142114D));
    }

}
