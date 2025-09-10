package khc.springboot.project7.total.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project7.total.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

	int deleteByMember_Id(Long id);
	
	
	
}
