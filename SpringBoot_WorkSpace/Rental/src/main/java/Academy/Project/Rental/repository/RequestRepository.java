package Academy.Project.Rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.domain.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

	List<Request> findByPlace(Place place);



}
