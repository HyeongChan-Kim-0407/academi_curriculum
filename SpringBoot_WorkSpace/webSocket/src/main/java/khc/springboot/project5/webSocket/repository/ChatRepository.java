package khc.springboot.project5.webSocket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project5.webSocket.domain.Message;

public interface ChatRepository extends JpaRepository<Message, Long> {

	List<Message> findAllByOrderByIdAsc();
	
	
	
}
