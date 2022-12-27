package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soixam.model.Orders;
@Repository
public interface IOrdersRepository extends JpaRepository<Orders,Long> {
}
