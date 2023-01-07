package soixam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productDetail")
public class ProductDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productDetailId")
    private Long productDetailId;
    @Column (name = "detailStatus")
    private boolean detailStatus;
    @Column (name = "quantity")
    private int quantity;
    @Column (name = "dateDetail")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDetail;
    @ManyToOne
    @JoinColumn(name = "colorId")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;
    @OneToMany(mappedBy = "productDetail")
    @JsonIgnore
    private List<CartDetail> cartDetails = new ArrayList<>();
}
