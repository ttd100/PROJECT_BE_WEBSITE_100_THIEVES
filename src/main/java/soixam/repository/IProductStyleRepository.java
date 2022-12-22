package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soixam.model.Category;
import soixam.model.ProductStyle;

import java.util.List;
import java.util.Optional;

public interface IProductStyleRepository extends JpaRepository<ProductStyle,Long> {
    Boolean existsByName(String name);

    @Query("select o from ProductStyle o where o.category.idCategory=?1")
    List<ProductStyle> findAllByIdCategory(Long idCategory);

    List<ProductStyle> findAllByNameContaining(String name);

    Iterable<ProductStyle> findAllByCategory(Category category);

    void deleteById(Long id);

    Optional<ProductStyle> findById(Long id);
}

