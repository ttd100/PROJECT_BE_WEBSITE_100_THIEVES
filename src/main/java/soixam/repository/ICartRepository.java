package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soixam.model.Cart;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUsers_UserId(Long userId);
}
