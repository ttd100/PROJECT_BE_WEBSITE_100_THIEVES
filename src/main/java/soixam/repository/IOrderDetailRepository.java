package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soixam.model.OrderDetail;
@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long>{
//    @Query("select new ReportBestSellingProduct")
}
