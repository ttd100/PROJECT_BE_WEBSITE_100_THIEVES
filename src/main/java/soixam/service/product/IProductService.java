package soixam.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import soixam.model.Category;
import soixam.model.Product;
import soixam.service.IGeneric;

import java.util.List;

public interface IProductService extends IGeneric<Product> {
//    Page<Product> findAll(Pageable pageable);
    List<Product> findAllByNameContaining(String name);

    Boolean existsByName(String name);
    Iterable<Product> findByCategory(Category category);
    Product findByCategoryOrName(String name);
}
