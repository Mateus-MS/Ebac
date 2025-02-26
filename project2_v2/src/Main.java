import connection.DBConnection;
import generics.DAO.GenericDAO;
import generics.service.GenericService;
import services.client.Client;
import services.product.Product;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection dbcon = DBConnection.getInstance();
        System.out.println(dbcon);

        GenericDAO<Product, Double> productDAO = new GenericDAO<>();
        GenericService<Product, Double> productService = new GenericService<>(productDAO);

        var product = new Product(
                "Playstation",
                1,
                3500,
                true
        );

        productService.register(product);

        GenericDAO<Client, String> clientDAO = new GenericDAO<>();
        GenericService<Client, String> clientService = new GenericService<>(clientDAO);

        var client = new Client(
            "Mateus",
            24,
            "Rua palmira",
            'M',
            "123123123"
        );

        clientService.register(client);

    }
}