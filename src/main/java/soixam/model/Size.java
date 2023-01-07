package soixam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sizeId")
    private Long sizeId;
    @Column(name = "sizeName")
    private String sizeName;
    @Column(name = "sizeStatus")
    private boolean sizeStatus;
    @OneToMany(mappedBy = "size")
    @JsonIgnore
    List<ProductDetail> productDetailList = new ArrayList<>();
}
