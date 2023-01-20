package kg.megacom.beauty_salon.dao;

import kg.megacom.beauty_salon.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRep extends JpaRepository<Order,Long> {

    List<Order>findOrderByMaster_Id(Long masterId);

    @Query("select o from Order as o where o.confirmCode=:confirmCode")
    Order findOrderByCode(int confirmCode);

}
