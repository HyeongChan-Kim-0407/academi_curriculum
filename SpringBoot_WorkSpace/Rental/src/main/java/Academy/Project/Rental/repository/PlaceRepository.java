package Academy.Project.Rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Academy.Project.Rental.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

	List<Place> findByPtype(String ptype);

	List<Place> findByPtitleContainingAndPtypeContainingAndPlocationContaining(String ptitle, String ptype,
			String plocation);
}
