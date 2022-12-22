package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soixam.model.Category;
import soixam.model.Product;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findByCategory(Category category);
    List<Product> findAllByNameProductContaining(String name);
    Boolean existsByNameProduct(String name);

    @Query("select o from Product o where o.category.nameCategory=?1")
    List<Product> findAllByNameCategory(String nameCategory);
}
