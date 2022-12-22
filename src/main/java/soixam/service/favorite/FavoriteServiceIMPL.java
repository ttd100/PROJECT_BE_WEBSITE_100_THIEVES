package soixam.service.favorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.Favorite;
import soixam.repository.IFavoriteRepository;

import java.util.List;
import java.util.Optional;
@Service
public class FavoriteServiceIMPL implements IFavoriteService{
    @Autowired
    private IFavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Optional<Favorite> findById(Long id) {
        return favoriteRepository.findById(id);
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteById(Long id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public Long checkExistsByIdProductAndUser(Long idProduct, Long id) {
        return favoriteRepository.checkExistsByIdProductAndUser(idProduct,id);
    }
}
