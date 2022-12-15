package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soixam.model.Category;
import soixam.model.Product;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findByCategory(Category category);
    List<Product> findAllByNameContaining(String name);
    Boolean existsByName(String name);
    @Query(value = "select * from products pr join category ca on pr.idcat = ca.id where ca.name = :principal or pr.name = :principal",
            nativeQuery = true)
    Product findByCategoryOrName(@Param("principal")String principal);

}
