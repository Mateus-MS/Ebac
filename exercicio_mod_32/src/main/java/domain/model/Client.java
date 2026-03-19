package domain.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_clients")
public class Client {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false, unique = true)
    private UUID id;

    public UUID getId(){return this.id;}

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> cart;

    public void setCart(List<Product> prods){this.cart = prods;}
    public List<Product> getCart(){return this.cart;}

    public Client(){}

    public Client(String name){
        this.name = name;
    }
}
