package main.test.service;

import main.java.client.Client;
import main.java.client.DAO.ClientDAOMock;
import main.java.client.service.ClientService;
import main.java.generics.DAO.IGenericDAO;
import main.java.generics.service.IGenericService;
import main.test.generic.GenericServiceTest;

public class ClientServiceTest extends GenericServiceTest<Client> {

    @Override
    protected IGenericDAO<Client> createDAO() {
        return ClientDAOMock.getInstance();
    }

    @Override
    protected IGenericService<Client> createService(IGenericDAO<Client> dao) {
        return new ClientService(dao);
    }

    @Override
    protected Client createTestEntity(Double code) {
        return new Client(
                "Test",
                20,
                "R. Test",
                'M',
                code
        );
    }
}
