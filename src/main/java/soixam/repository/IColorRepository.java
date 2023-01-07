package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soixam.model.Color;

import java.util.Set;

@Repository
public interface IColorRepository extends JpaRepository<Color,Long> {

}
