package INT222.Controllers;

import INT222.Exceptions.NotFoundRatingException;
import INT222.Models.RatingOfProduct;
import INT222.Models.Ratings;
import INT222.Models.Reviews;
import INT222.Repositories.RatingOfProductRepository;
import INT222.Repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingOfProductRepository ratingOfProductRepository;

    @GetMapping("/list")
    public List<Ratings> getRating() {
        return ratingRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable(value = "id") long id) {
        ratingRepository.deleteById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public void editRating(@RequestBody Ratings ratings) {
        if(ratingRepository.existsById(ratings.getId())){
            ratingRepository.save(ratings);
        }else throw new NotFoundRatingException(ratings.getId());

    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public void addRating(@RequestBody Ratings ratings) {
        if (ratingRepository.findTopByOrderByIdDesc() == null) {
            ratings.setId(1);
            ratingRepository.save(ratings);
        }else
            ratings.setId(ratingRepository.findTopByOrderByIdDesc().getId()+1);
        ratingRepository.save(ratings);
    }

    @GetMapping("/listByProductId/{id}")
    public List<RatingOfProduct> getRatingByProductId(@PathVariable(value = "id") long id) {
        return ratingOfProductRepository.getAllByProductId(id);
    }

}
