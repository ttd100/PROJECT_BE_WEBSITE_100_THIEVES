package soixam.service.cartDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.CartDetail;
import soixam.repository.CartDetailRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CartDetailServiceIMPL implements ICartDetailService{
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public void deleteById(Long id) {
        cartDetailRepository.deleteById(id);
    }

    @Override
    public CartDetail findByProductDetail_ProductDetailIdAndCart_Id(Long productDetailId, Long cartId) {
        return cartDetailRepository.findByProductDetail_ProductDetailIdAndCart_Id(productDetailId,cartId);
    }
}
