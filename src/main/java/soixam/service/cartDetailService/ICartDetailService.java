package soixam.service.cartDetailService;

import soixam.model.CartDetail;
import soixam.service.IGeneric;

public interface ICartDetailService extends IGeneric<CartDetail> {
    CartDetail findByProductDetail_ProductDetailIdAndCart_Id(Long productDetailId, Long cartId);
}
