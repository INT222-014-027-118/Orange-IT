package INT222.Controllers;

import INT222.Exceptions.NotFoundException;
import INT222.Models.*;
import INT222.Repositories.RatingOfProductForAddRepository;
import INT222.Repositories.ReviewForAddRepository;
import INT222.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewForAddRepository reviewForAddRepository;

    @Autowired
    private RatingOfProductForAddRepository ratingOfProductForAddRepository;

    @GetMapping("/getByProductId/{id}")
    public List<Reviews> getReviewByProductId(@PathVariable(value = "id") long id) {
        List<Reviews> reviews = new ArrayList<Reviews>();
        List<Reviews> reviewsList = reviewRepository.findAll();
        for (int i = 0; i < reviewsList.size(); i++) {
            List<RatingOfProduct> ratingOfProducts = reviewsList.get(1).getRatingOfProducts();
            for (int j = 0; j < ratingOfProducts.size(); j++) {
                if(ratingOfProducts.get(j).getProductId()== id){
                    reviews.add(reviewsList.get(i));
                }
            }
        }
        return reviews;
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
                ratingOfProductForAddRepository.deleteByReviewId(id);
               reviewForAddRepository.deleteById(id);
            }
        }
    }





    @PostMapping("/add")
    @PreAuthorize("hasRole('User')")
    public void addReview(@RequestBody ReviewForAdd reviews) {
        if (reviewForAddRepository.findTopByOrderByIdDesc() == null) {
            reviews.setId(1);
            for (int i = 0; i < reviews.getRatingOfProductForAdds().size(); i++) {
                reviews.getRatingOfProductForAdds().get(i).setReviewId(1);
                if(ratingOfProductForAddRepository.findTopByOrderByIdDesc() == null ){
                    reviews.getRatingOfProductForAdds().get(i).setId(1);
                }else
                reviews.getRatingOfProductForAdds().get(i).setId(ratingOfProductForAddRepository.findTopByOrderByIdDesc().getId()+1+i);

            }
            ZoneId zone = ZoneId.of("Asia/Bangkok");
            LocalDateTime now = LocalDateTime.now(zone);
            reviews.setReviewDate(now);
            reviewForAddRepository.save(reviews);
        }else
            reviews.setId(reviewForAddRepository.findTopByOrderByIdDesc().getId()+1);
        for (int i = 0; i < reviews.getRatingOfProductForAdds().size(); i++) {
            reviews.getRatingOfProductForAdds().get(i).setReviewId(reviews.getId());
            if(ratingOfProductForAddRepository.findTopByOrderByIdDesc() == null ){
                reviews.getRatingOfProductForAdds().get(i).setId(1);
            }else
                reviews.getRatingOfProductForAdds().get(i).setId(ratingOfProductForAddRepository.findTopByOrderByIdDesc().getId()+1+i);

        }
        ZoneId zone = ZoneId.of("Asia/Bangkok");
        LocalDateTime now = LocalDateTime.now(zone);
        reviews.setReviewDate(now);
        reviewForAddRepository.save(reviews);
    }



}
