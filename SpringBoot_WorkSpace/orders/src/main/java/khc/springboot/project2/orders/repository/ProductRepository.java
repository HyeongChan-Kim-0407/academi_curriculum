package khc.springboot.project2.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project2.orders.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	 

}
