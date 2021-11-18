package INT222.Controllers;

import INT222.Exceptions.NotFoundRatingException;
import INT222.Models.Ratings;
import INT222.Models.Reviews;
import INT222.Repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "*")
public class RatingController {
    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping("/list")
    public List<Ratings> getReview() {
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


    @GetMapping("/getByCategoryId/{id}")
    public List<Ratings> getRatingsByCategoryId(@PathVariable(value = "id") long id) {
        return ratingRepository.findAllByCategoryId(id);

    }
}
