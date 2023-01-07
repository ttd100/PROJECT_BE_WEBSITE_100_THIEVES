package soixam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity

@Table( name = "carts")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NaturalId
    @Column(name = "cartStatus")
    private int cartStatus;
    @NotNull
    private Integer quantity;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date creationTime;
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="UPDATED_TIME")
    private Date updatedTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User users;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;
//    @ManyToMany(fetch = FetchType.EAGER)
//
//    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
//    List<Product> products = new ArrayList<>();
    @PrePersist
    private void onCreate(){
        this.creationTime = new Date();
    }
    @PreUpdate
    private void onUpdate(){
        this.updatedTime = new Date();
    }
}
