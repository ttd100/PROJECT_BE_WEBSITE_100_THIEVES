package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soixam.model.Category;
import soixam.model.ProductStyle;

import java.util.List;

@Repository
public interface IProductStyleRepository extends JpaRepository<ProductStyle,Long> {
    Boolean existsByNameProductStyle(String name);

    @Query("select o from ProductStyle o where o.category.idCategory=?1")
    List<ProductStyle> findAllByIdCategory(Long idCategory);

    List<ProductStyle> findAllByNameProductStyleContaining(String name);

    Iterable<ProductStyle> findAllByCategory(Category category);

}

