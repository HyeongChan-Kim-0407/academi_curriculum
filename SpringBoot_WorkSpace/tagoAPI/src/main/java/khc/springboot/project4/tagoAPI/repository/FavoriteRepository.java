package khc.springboot.project4.tagoAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project4.tagoAPI.domain.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

	Favorite findByNodeidAndCitycodeAndKakaoid(String nodeid, String citycode, String loginUser);

	List<Favorite> findByKakaoid(String loginUser);

}
