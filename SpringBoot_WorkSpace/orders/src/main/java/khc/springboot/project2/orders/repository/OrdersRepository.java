package khc.springboot.project2.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project2.orders.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
