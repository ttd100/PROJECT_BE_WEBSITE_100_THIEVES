package soixam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soixam.dto.reponse.ProductStyleDTO;
import soixam.dto.reponse.ResponseMessage;
import soixam.model.Category;
import soixam.model.ProductStyle;
import soixam.model.User;
import soixam.security.userpincal.UserDetailService;
import soixam.service.productStyle.IProductStyleService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/productStyle")
@RestController
@CrossOrigin
public class ProductStyleController {
@Autowired
    private IProductStyleService productStyleService;
@Autowired
     private UserDetailService userDetailService;
    @PostMapping("/create")
    public ResponseEntity<?> createProductStyle(@Valid @RequestBody ProductStyle productStyle){
        User user = userDetailService.getCurrentUser();
        if (user.getUsername().equals("Anonymous")){
            return new ResponseEntity<>(new ResponseMessage("chua_login"), HttpStatus.OK);
        }
        if (productStyleService.existsByNameProduct(productStyle.getNameProductStyle())){
            return new ResponseEntity<>(new ResponseMessage("Category_invalid"),HttpStatus.OK);
        }
        productStyle.setUser(user);
        productStyleService.save(productStyle);
        return new ResponseEntity<>(new ResponseMessage("create_success!!!"),HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<?>ShowListProductStyle(){
        List<ProductStyle> productStyleList = productStyleService.findAll();
        return new ResponseEntity<>(productStyleList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getId(@PathVariable Long id){
        if (!productStyleService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productStyleService.findById(id).get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteProStyle(@PathVariable Long id){
        Optional<ProductStyle> productStyleList = productStyleService.findById(id);
        if (!productStyleList.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("not_found"),HttpStatus.OK);
        }
        productStyleService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("delete_success"),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateProStyle(@PathVariable Long id ,@RequestBody ProductStyle productStyle){
        Optional<ProductStyle>productStyle1 = productStyleService.findById(id);
        if (!productStyle1.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("not_found"),HttpStatus.OK);
        }
        if (productStyle.getNameProductStyle().trim().equals("")){
            return new ResponseEntity<>(new ResponseMessage("name_category_invalid"),HttpStatus.OK);
        }
        productStyle1.get().setNameProductStyle(productStyle.getNameProductStyle());
        productStyleService.save(productStyle1.get());
        return new ResponseEntity<>(new ResponseMessage("update_success"),HttpStatus.OK);
    }
    @GetMapping("search/{name}")
    public ResponseEntity<?>searchNameProStyle(@PathVariable String name){
        if (name.trim().equals("")){
            return new ResponseEntity<>(new ResponseMessage("not_found"),HttpStatus.OK);
        }
        return new ResponseEntity<>(productStyleService.findAllByNameProductContaining(name),HttpStatus.OK);
    }
}
