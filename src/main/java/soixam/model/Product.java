package soixam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 5, max = 50)
    String name;
    @NumberFormat
    @Min(0)
    private float price;
    @Lob
    @NotNull
    private String avatar;
    @NumberFormat
    @Min(0)
    private int quantity;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;
    //chien
}
