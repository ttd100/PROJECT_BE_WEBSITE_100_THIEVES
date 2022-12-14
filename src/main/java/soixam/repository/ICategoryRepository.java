package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soixam.model.Category;
@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
    Boolean existsByName(String name);
}
