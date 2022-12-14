package soixam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soixam.dto.reponse.ResponseMessage;
import soixam.model.Category;
import soixam.model.User;
import soixam.security.userpincal.UserDetailService;
import soixam.service.category.ICategoryService;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("/category")
@RestController
@CrossOrigin
public class CateController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private UserDetailService userDetailService;
    @PostMapping("/create")
    public ResponseEntity<?>createCate(@Valid @RequestBody Category category){
        User user = userDetailService.getCurrentUser();
        if (user.getUsername().equals("Anonymous")){
            return new ResponseEntity<>(new ResponseMessage("chua login"), HttpStatus.OK);
        }
        if (categoryService.existsByName(category.getName())){
            return new ResponseEntity<>(new ResponseMessage("Category_invalid"),HttpStatus.OK);
        }
        if (category.getAvatar().trim().equals("")){
            return new ResponseEntity<>(new ResponseMessage("avatar_null"),HttpStatus.OK);
        }
        category.setUser(user);
        categoryService.save(category);
        return new ResponseEntity<>(new ResponseMessage("create_success!!!"),HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<?>ShowListCate(){
        List<Category>categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }
    @GetMapping("/id")
    public ResponseEntity<?>getId(@PathVariable Long id){
        if (!categoryService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.findById(id).get(),HttpStatus.OK);
    }
}
