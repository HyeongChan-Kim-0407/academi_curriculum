package khc.springboot.project7.total.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project7.total.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByMid(String mid);

	Member findByMidAndMpw(String mid, String mpw);

}
