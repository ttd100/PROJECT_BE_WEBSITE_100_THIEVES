package soixam.service.productDetail;

import soixam.model.ProductDetail;
import soixam.service.IGeneric;

public interface IProductDetailService extends IGeneric<ProductDetail> {
    boolean existsByColor_ColorIdAndAndSize_SizeIdAndProduct_IdProduct(Long colorId,Long sizeId,Long productId);
    ProductDetail findByColor_ColorIdAndSize_SizeIdAndProduct_IdProduct(Long colorId,Long sizeId,Long productId);
}
