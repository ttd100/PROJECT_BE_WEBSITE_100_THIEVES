package soixam.service.favorite;

import soixam.model.Favorite;
import soixam.service.IGeneric;

public interface IFavoriteService extends IGeneric<Favorite> {
    Long checkExistsByIdProductAndUser(Long idProduct,Long id);
}