package domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false, unique = true)
    private UUID id;

    public UUID getId(){return this.id;}

    @Column(name = "title")
    private String title;

    public void setTitle(String title){this.title = title;}
    public String getTitle(){return this.title;}

    @Column(name = "price")
    private Float price;

    public void setPrice(Float price){this.price = price;}
    public Float getPrice(){return this.price;}

    @Column(name = "stock")
    private Integer stock;

    public void setStock(Integer stock){this.stock = stock;}
    public Integer getStock(){return this.stock;}

    @ManyToOne
    @JoinColumn(
            name = "client_id",
            foreignKey = @ForeignKey(name = "fk_client_product"),
            referencedColumnName = "id",
            nullable = false
    )
    private Client client;

    public Product(){}

    public Product(String title, Float price, Integer stock){
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

}
