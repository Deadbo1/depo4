package application.repository;

import application.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao extends JpaRepository<Orders,Long> {

    List<Orders> findAll();

    Orders getOrdersById(Long id);
}
