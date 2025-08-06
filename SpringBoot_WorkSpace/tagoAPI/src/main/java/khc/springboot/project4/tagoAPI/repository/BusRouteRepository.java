package khc.springboot.project4.tagoAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project4.tagoAPI.domain.BusRoute;

public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {

	List<BusRoute> findByCitycodeAndRouteid(String cityCode, String routeId);

}
