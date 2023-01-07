package soixam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailModel {
    private Long cartId;
    private Long productDetailId;
    private Long detailCartQuantity;
    private float priceCurrentDetail;
}
