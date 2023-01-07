package soixam.service.productDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.ProductDetail;
import soixam.repository.IProductDetailRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ProductDetailServiceIMPL implements IProductDetailService{
    @Autowired
    private IProductDetailRepository productDetailRepository;
    @Override
    public List<ProductDetail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public Optional<ProductDetail> findById(Long id) {
        return productDetailRepository.findById(id);
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public void deleteById(Long id) {
        productDetailRepository.deleteById(id);
    }

    @Override
    public boolean existsByColor_ColorIdAndAndSize_SizeIdAndProduct_IdProduct(Long colorId, Long sizeId, Long productId) {
        return productDetailRepository.existsByColor_ColorIdAndAndSize_SizeIdAndProduct_IdProduct(colorId,sizeId,productId);
    }

    @Override
    public ProductDetail findByColor_ColorIdAndSize_SizeIdAndProduct_IdProduct(Long colorId, Long sizeId, Long productId) {
        return productDetailRepository.findByColor_ColorIdAndSize_SizeIdAndProduct_IdProduct(colorId,sizeId,productId);
    }
}
