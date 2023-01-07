package soixam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "colorId")
    private Long colorId;
    @Column(name = "colorName", unique = true)
    private String colorName;
    @Column(name = "colorHex")
    private String colorHex;
    @Column(name = "colorStatus")
    private boolean colorStatus;
    @OneToMany(mappedBy = "color")
    @JsonIgnore
    List<ProductDetail> productDetailList = new ArrayList<>();
}
