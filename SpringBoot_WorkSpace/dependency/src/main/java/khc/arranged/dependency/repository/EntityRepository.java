package khc.arranged.dependency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.arranged.dependency.domain.EntityClass;

public interface EntityRepository extends JpaRepository<EntityClass, Long> {

	List<EntityClass> findByName(String string);

	List<EntityClass> findByEmail(String string);

	List<EntityClass> findByNameAndEmail(String string, String string2);

}
