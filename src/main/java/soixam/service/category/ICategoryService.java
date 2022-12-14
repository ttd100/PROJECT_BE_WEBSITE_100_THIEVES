package soixam.service.category;

import soixam.model.Category;
import soixam.service.IGeneric;

public interface ICategoryService extends IGeneric<Category> {
    Boolean existsByName(String name);
}
