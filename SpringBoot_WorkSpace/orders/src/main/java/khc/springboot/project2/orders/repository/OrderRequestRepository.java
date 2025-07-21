package khc.springboot.project2.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project2.orders.domain.Member;
import khc.springboot.project2.orders.domain.OrderRequest;
import khc.springboot.project2.orders.domain.Product;

public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long> {

	OrderRequest findByProductAndMember(Product product, Member member);

	List<OrderRequest> findByProduct(Product product);
	
}
