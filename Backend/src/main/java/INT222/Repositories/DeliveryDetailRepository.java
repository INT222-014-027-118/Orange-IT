package INT222.Repositories;

import INT222.Models.DeliveryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetails,Long> {

    List<DeliveryDetails> findAllByUserId(long userId);

    DeliveryDetails findTopByOrderByIdDesc();

}
