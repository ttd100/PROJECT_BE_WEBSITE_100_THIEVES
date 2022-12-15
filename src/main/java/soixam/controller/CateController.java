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
import java.util.Optional;

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
            return new ResponseEntity<>(new ResponseMessage("chua_login"), HttpStatus.OK);
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
    @GetMapping("/{id}")
    public ResponseEntity<?>getId(@PathVariable Long id){
        if (!categoryService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.findById(id).get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteCate(@PathVariable Long id){
        Optional<Category>category = categoryService.findById(id);
        if (!category.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("not_found"),HttpStatus.OK);
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("delete_success"),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateCate(@PathVariable Long id ,@RequestBody Category category){
        Optional<Category>category1 = categoryService.findById(id);
        if (!category1.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("not_found"),HttpStatus.OK);
        }
        if (category.getName().trim().equals("")){
            return new ResponseEntity<>(new ResponseMessage("name_category_invalid"),HttpStatus.OK);
        }
        if (category.getAvatar().trim().equals("")){
            return new ResponseEntity<>(new ResponseMessage("avatar_null"),HttpStatus.OK);
        }
        category1.get().setName(category.getName());
        category1.get().setAvatar(category.getAvatar());
        categoryService.save(category1.get());
        return new ResponseEntity<>(new ResponseMessage("update_success"),HttpStatus.OK);
    }
    @GetMapping("search/{name}")
    public ResponseEntity<?>searchNameCate(@PathVariable String name){
        if (name.trim().equals("")){
            return new ResponseEntity<>(new ResponseMessage("not_found"),HttpStatus.OK);
        }
        return new ResponseEntity<>(categoryService.findAllByNameContaining(name),HttpStatus.OK);
    }
}
