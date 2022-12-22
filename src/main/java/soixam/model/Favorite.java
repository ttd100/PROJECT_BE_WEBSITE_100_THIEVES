package soixam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "favorite")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFavorite;
//    @Column(nullable = false)
//    @CreatedDate
//    @LastModifiedDate
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date likeDate;
    @ManyToOne @JoinColumn(name = "idproduct")
    private Product product;
    @ManyToOne @JoinColumn(name = "id")
    private User user;
}
