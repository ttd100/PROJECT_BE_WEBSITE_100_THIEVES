package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soixam.model.ProductDetail;
@Repository
public interface IProductDetailRepository extends JpaRepository<ProductDetail,Long> {
    boolean existsByColor_ColorIdAndAndSize_SizeIdAndProduct_IdProduct(Long colorId,Long sizeId,Long productId);
    ProductDetail findByColor_ColorIdAndSize_SizeIdAndProduct_IdProduct(Long colorId,Long sizeId,Long productId);
}
