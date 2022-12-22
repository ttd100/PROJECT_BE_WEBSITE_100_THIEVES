package soixam.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer idOrder;
    @Column( nullable = false )
    @CreatedDate
    private Date createDate;
    @Column( nullable = false, columnDefinition = "nvarchar(100)" )
    private String address;
    @ManyToOne
    @JoinColumn( name = "username" )
    private User user;
    @OneToMany( fetch = FetchType.LAZY )
    List<OrderDetail> orderDetails;
}
