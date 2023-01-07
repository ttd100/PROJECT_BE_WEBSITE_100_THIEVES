package soixam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soixam.model.User;
import soixam.security.userpincal.UserDetailService;
import soixam.service.orderdetail.IOrderDetailService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private UserDetailService userDetailService;
    @GetMapping
    public ResponseEntity<?> allOrderOfUser(){
        User user = userDetailService.getCurrentUser();
        return ResponseEntity.ok(orderDetailService.getOrderDetailByUser(user.getUserId()));
    }
}
