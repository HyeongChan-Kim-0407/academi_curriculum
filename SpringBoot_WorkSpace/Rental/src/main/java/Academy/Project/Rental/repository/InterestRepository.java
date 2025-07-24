package Academy.Project.Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Academy.Project.Rental.domain.Interests;

public interface InterestRepository extends JpaRepository<Interests, Long> {

}
