package Academy.Project.Rental.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.domain.RequestAccept;

public interface RequestAcceptRepository extends JpaRepository<RequestAccept, Long> {

	List<RequestAccept> findByBdateAndPlace(LocalDate bdate, Place place);

	

}
