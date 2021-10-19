package INT222.Repositories;

import INT222.Models.Shippings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingRepository extends JpaRepository<Shippings,Long> {

    Shippings findTopByOrderByIdDesc();

    List<Shippings> findAllByDeliveryDetailId(long id);
}
