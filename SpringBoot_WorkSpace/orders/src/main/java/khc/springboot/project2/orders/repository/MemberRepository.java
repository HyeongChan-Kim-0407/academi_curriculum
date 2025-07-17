package khc.springboot.project2.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project2.orders.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByMidAndMpw(String mid, String mpw);

	Member findByMid(String loginId);

}
