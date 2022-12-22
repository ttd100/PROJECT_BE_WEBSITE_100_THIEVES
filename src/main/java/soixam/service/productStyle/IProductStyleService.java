package soixam.service.productStyle;

import soixam.dto.reponse.ProductStyleDTO;
import soixam.model.Category;
import soixam.model.Product;
import soixam.model.ProductStyle;
import soixam.service.IGeneric;

import java.util.List;
import java.util.Optional;

public interface IProductStyleService extends IGeneric<ProductStyle> {
    List<ProductStyle> findAllByNameContaining(String name);

    Boolean existsByName(String name);
    Iterable<ProductStyle> findAllByCategory(Category category);
    List<ProductStyle> findAllByIdCategory(Long idCategory);
}
