package soixam.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soixam.dto.reponse.ResponseMessage;
import soixam.dto.request.CartDetailModel;
import soixam.model.*;
import soixam.security.userpincal.UserDetailService;
import soixam.service.cart.ICartService;
import soixam.service.cartDetailService.ICartDetailService;
import soixam.service.orders.IOrdersService;
import soixam.service.product.IProductService;
import soixam.service.productDetail.IProductDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/cart")
@AllArgsConstructor
@RestController
@CrossOrigin
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private ICartDetailService cartDetailService;
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private IProductService productService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IOrdersService ordersService;

    @PutMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartDetailModel cartDetailModel, @RequestParam("action") String action) {
        CartDetail cartDetail = null;
        cartDetail = cartDetailService.findByProductDetail_ProductDetailIdAndCart_Id(cartDetailModel.getProductDetailId(), cartDetailModel.getCartId());
        try {
            cartDetail = cartDetailService.findByProductDetail_ProductDetailIdAndCart_Id(cartDetailModel.getProductDetailId(), cartDetailModel.getCartId());
            if (cartDetail != null) {
                if (action.equals("add more")) {
                    cartDetail.setDetailCartQuantity(cartDetail.getDetailCartQuantity() + cartDetailModel.getDetailCartQuantity());
                } else if (action.equals("edit")) {
                    cartDetail.setDetailCartQuantity(cartDetailModel.getDetailCartQuantity());
                }
            } else {
                    int quantity = 1;
                cartDetail = new CartDetail();
                cartDetail.setCart(cartService.findById(cartDetailModel.getCartId()).get());
                cartDetail.setDetailCartQuantity((long) quantity);
                cartDetail.setProductDetail(productDetailService.findById(cartDetailModel.getProductDetailId()).get());
                cartDetail.setCartDetailStatus(true);
                cartDetail.setPriceCurrent(cartDetailModel.getPriceCurrentDetail());
            }
            cartDetailService.save(cartDetail);
            return ResponseEntity.ok().body("add product to cart successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("add product to cart error");
        }
    }
    @DeleteMapping("/deleteCartDetail/{id}")
    public ResponseEntity<?> deleteCartDetail(@RequestParam Long detailId){
        try {
            cartDetailService.deleteById(detailId);
            return ResponseEntity.ok().body("delete successfully");
        }catch (Exception e){
            return ResponseEntity.ok().body("delete error");
        }
    }
    @GetMapping("/cartDetail")
    public ResponseEntity<?> showList(){
        List<CartDetail> cartDetails = cartDetailService.findAll();
        return new ResponseEntity<>(cartDetails,HttpStatus.OK);
    }

}



//    @PostMapping("/{id}")
//    public ResponseEntity<?>addCart(@PathVariable("id") Long idProduct,@RequestBody(required = false)Optional<Map<Long,Cart>> existCart){
//        Map<Long,Cart> carts = existCart.orElse(new HashMap<>());
//        Optional<Product> productOptional = productService.findById(idProduct);
//        if (!productOptional.isPresent()){
//            return new ResponseEntity<>(new ResponseMessage("product_not"),HttpStatus.NOT_FOUND);
//        }
//        if (carts.containsKey(idProduct)){ // nếu id sp  trùng nhau thì tăng số lượng lên 1
//            Cart c = carts.get(idProduct);
//            c.setQuantity(c.getQuantity()+1);
//            carts.put(idProduct,c);
//        }else {
//            // nếu không có sp trong giỏ thì tạo mới
//            Cart cart = new Cart();
//            try {
//                cart.setQuantity(1);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            carts.put(idProduct,cart);
//        }
//
//        return ResponseEntity.ok(carts);
//    }
//    @GetMapping
//    public ResponseEntity<?> showListCart(@RequestBody(required = false)Optional<Map<Long,Cart>> existCart){
//        Map<Long,Cart> carts = existCart.orElse(new HashMap<>());
//        return new ResponseEntity<>(carts, HttpStatus.OK);
//    }



