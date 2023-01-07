package soixam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class CartDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartDetailId")
    private Long cartDetailId;
    @Column(name = "priceCurrent")
    private float priceCurrent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productDetailId")
    private ProductDetail productDetail;
    @Column(name = "detailCartQuantity")
    private Long detailCartQuantity;
    @JoinColumn(name = "cartDetailStatus")
    private boolean cartDetailStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Cart cart;
}
