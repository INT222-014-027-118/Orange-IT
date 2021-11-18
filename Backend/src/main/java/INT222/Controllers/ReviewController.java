package INT222.Controllers;

import INT222.Exceptions.NotFoundException;
import INT222.Models.Products;
import INT222.Models.ReviewForAdd;
import INT222.Models.Reviews;
import INT222.Repositories.ReviewForAddRepository;
import INT222.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewForAddRepository reviewForAddRepository;

    @GetMapping("/getByProductId/{id}")
    public List<Reviews> getReviewByProductId(@PathVariable(value = "id") long id) {
        List<Reviews> reviews = new ArrayList<>();
        for (int i = 0; i < reviewRepository.findAll().size(); i++) {
            for (int j = 0; j < reviewRepository.findAll().get(i).getRatingOfProducts().size(); j++) {

                if (reviewRepository.findAll().get(i).getRatingOfProducts().get(j).getProduct_id() == id) {
                    reviews.add(reviewRepository.findAll().get(i));
                }


            }

        }return reviews;
    }

    @GetMapping("/list")
    public List<ReviewForAdd> getReview() {
       return reviewForAddRepository.findAll();
    }

    @DeleteMapping("/{id}/{userId}")
    @PreAuthorize("hasRole('User')" +
            " || hasRole('Admin')" )
    public void deleteById(@PathVariable(value = "id") long id,@PathVariable(value = "userId") long userId) {
        if(reviewForAddRepository.existsByUserId(userId)){
            if(reviewForAddRepository.existsById(id)){
                reviewRepository.deleteById(id);
            }
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('User')")
    public void editReview(@RequestBody ReviewForAdd reviews) {
        if(reviewForAddRepository.existsByUserId(reviews.getUserId())){
            if(reviewForAddRepository.existsById(reviews.getId())){
                reviewForAddRepository.save(reviews);
            }
        }


    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('User')")
    public void addReview(@RequestBody ReviewForAdd reviews) {
        if (reviewForAddRepository.findTopByOrderByIdDesc() == null) {
            reviews.setId(1);
            reviewForAddRepository.save(reviews);
        }else
            reviews.setId(reviewForAddRepository.findTopByOrderByIdDesc().getId()+1);
        reviewForAddRepository.save(reviews);
    }



}
