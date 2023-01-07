package soixam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    @NotBlank
    @Size(min = 5, max = 50)
    String nameProduct;
    @NumberFormat
    @Min(0)
    private float price;
    @Lob
    @NotNull
    private String avatar;
    @NumberFormat
    @Min(0)
    private int quantity;
    @ManyToOne @JoinColumn(name = "idcategory")
    private Category category;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    List<Favorite> favorites;
    @ManyToOne @JoinColumn(name = "idproductstyle")
    private ProductStyle productStyle;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "product")
    List<ProductDetail> detailList = new ArrayList<>();
}
