package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soixam.model.OrderDetail;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long>{
    @Query("select o from OrderDetail o where o.orders.user.userId=?1")
    List<OrderDetail> getOrderDetailByUser(Long idUser);
}
