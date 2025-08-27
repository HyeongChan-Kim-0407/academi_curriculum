package khc.arranged.dependency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.arranged.dependency.domain.SampleEntity;

public interface SampleRepository extends JpaRepository<SampleEntity, Long> {

	List<SampleEntity> findByUsername(String searchName);

	List<SampleEntity> findByUsernameContains(String searchName);

}
