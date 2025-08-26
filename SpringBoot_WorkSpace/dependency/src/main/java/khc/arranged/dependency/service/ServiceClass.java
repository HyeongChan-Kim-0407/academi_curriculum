package khc.arranged.dependency.service;

import java.util.List;

import org.springframework.stereotype.Service;

import khc.arranged.dependency.domain.EntityClass;
import khc.arranged.dependency.repository.EntityRepository;

@Service
public class ServiceClass {
	// repository
	private EntityRepository entityRepository;
	
	public String serviceMethod() {
		// 테이블에 insert 처리
		EntityClass newEntity = new EntityClass();
		newEntity.setName("이름");
		newEntity.setEmail("이메일");
		// entityRepository.save(저장할 데이터 (Entity 객체));
		entityRepository.save(newEntity);
		return null;
	}
	
	// 테이블의 데이터를 조회(select)하는 기능
	public void selectMethod() {
		// 데이터 조회
		List<EntityClass> ecList = entityRepository.findAll(); // 전체 조회
		// 조건부 조회
		// SELECT * FROM 테이블 WHERE 컬럼 = ?
		// repository.findBy컬럼(조건);
		
		// name이 "JPA"인 데이터 조회
		// SELECT * FROM 테이블 WHERE NAME = 'JPA'
		entityRepository.findByName("JPA");
		
		// email이 "JPA"인 데이터 조회
		// SELECT * FROM 테이블 WHERE EMAIL = 'JPA'
		entityRepository.findByEmail("JPA");
		
		// name이 "JPA", email이 "REPOSITORY"인 데이터 조회
		// SELECT * FROM 테이블 WHERE NAME = 'JPA' AND EMAIL = 'REPOSITORY'
		entityRepository.findByNameAndEmail("JPA", "REPOSITORY");
	}
	
	public void method1() {
		
	}
	
	public String method2() {
		
		return null;
	}

	
	// 리턴타입 메소드이름(매개변수,...) { 실행코드 }	

}
