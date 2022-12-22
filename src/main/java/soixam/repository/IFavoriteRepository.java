package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soixam.model.Favorite;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite,Long> {
    @Query("select o.idFavorite from Favorite o where o.product.idProduct=?1 and o.user.id=?2")
    Long checkExistsByIdProductAndUser(Long idProduct,Long id);
}
