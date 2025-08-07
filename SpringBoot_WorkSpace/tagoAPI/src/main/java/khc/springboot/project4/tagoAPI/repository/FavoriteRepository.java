package khc.springboot.project4.tagoAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project4.tagoAPI.domain.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
