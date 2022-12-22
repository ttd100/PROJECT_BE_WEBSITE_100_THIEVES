package soixam.service.category;

import soixam.model.Category;
import soixam.service.IGeneric;

import java.util.List;

public interface ICategoryService extends IGeneric<Category> {
    Boolean existsByName(String name);
    List<Category> findAllByNameContaining(String name);
}
