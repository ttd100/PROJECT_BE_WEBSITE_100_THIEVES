package soixam.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.Category;
import soixam.model.Product;
import soixam.repository.IProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements IProductService{
    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

//    @Override
//    public Page<Product> findAll(Pageable pageable) {
//        return productRepository.findAll(pageable);
//    }

    @Override
    public List<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameProductContaining(name);
    }

    @Override
    public Boolean existsByName(String name) {
        return productRepository.existsByNameProduct(name);
    }

    @Override
    public Iterable<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findAllByNameCategory(String nameCategory) {
        List<Product> products = productRepository.findAllByNameCategory(nameCategory);
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
