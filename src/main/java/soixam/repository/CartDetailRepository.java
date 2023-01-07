package soixam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soixam.model.CartDetail;
@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {
    CartDetail findByProductDetail_ProductDetailIdAndCart_Id(Long productDetailId, Long cartId);
}
