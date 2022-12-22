package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soixam.model.Category;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
    Boolean existsByNameCategory(String name);
    List<Category> findAllByNameCategoryContaining(String name);
}
