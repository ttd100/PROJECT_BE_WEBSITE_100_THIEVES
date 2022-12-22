package soixam.service.productStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.Category;
import soixam.model.ProductStyle;
import soixam.repository.IProductStyleRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ProductStyleServiceIMPL implements IProductStyleService{
    @Autowired
    private IProductStyleRepository iProductStyleRepository;


    @Override
    public List<ProductStyle> findAll() {
        return iProductStyleRepository.findAll();
    }

    @Override
    public Optional<ProductStyle> findById(Long id) {
        return iProductStyleRepository.findById(id);
    }

    @Override
    public ProductStyle save(ProductStyle productStyle) {
        return iProductStyleRepository.save(productStyle);
    }

    @Override
    public void deleteById(Long id) {
    iProductStyleRepository.deleteById(id);
    }

    @Override
    public List<ProductStyle> findAllByNameProductContaining(String name) {
        return iProductStyleRepository.findAllByNameProductStyleContaining(name);
    }

    @Override
    public Boolean existsByNameProduct(String name) {
        return iProductStyleRepository.existsByNameProductStyle(name);
    }

    @Override
    public Iterable<ProductStyle> findAllByCategory(Category category) {
        return iProductStyleRepository.findAllByCategory(category);
    }

    @Override
    public List<ProductStyle> findAllByIdCategory(Long idCategory) {
        return iProductStyleRepository.findAllByIdCategory(idCategory);
    }


}
