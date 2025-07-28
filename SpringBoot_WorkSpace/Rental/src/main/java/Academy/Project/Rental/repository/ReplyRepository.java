package Academy.Project.Rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.domain.Reply;
import Academy.Project.Rental.domain.Request;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByRequest(Request request);
    List<Reply> findByMember(Member member);
}
