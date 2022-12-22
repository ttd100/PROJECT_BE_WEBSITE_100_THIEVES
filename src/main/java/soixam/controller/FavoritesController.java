package soixam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soixam.dto.reponse.ResponseMessage;
import soixam.model.Favorite;
import soixam.service.favorite.IFavoriteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/favorite")
public class FavoritesController {
    @Autowired
    private IFavoriteService favoriteService;
    @GetMapping
    public ResponseEntity<?> getFavorites(){
        return ResponseEntity.ok(favoriteService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> createFavorites(@RequestBody Favorite favorite){
        Long liked = favoriteService.checkExistsByIdProductAndUser(favorite.getUser().getId(),favorite.getProduct().getIdProduct());
        if (liked!=null){ //nếu đã like thì sẽ unlike
            deleteFavorite(liked);
            return ResponseEntity.ok(false);
        }
        favoriteService.save(favorite);
        return new  ResponseEntity(new ResponseMessage("create_favorite"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteFavorite(@PathVariable Long id){
        favoriteService.deleteById(id);
    }
}
