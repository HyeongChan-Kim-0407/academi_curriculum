package Academy.Project.Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Academy.Project.Rental.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByMidAndMpw(String mid, String mpw);

	Member findByMid(String mid);

}