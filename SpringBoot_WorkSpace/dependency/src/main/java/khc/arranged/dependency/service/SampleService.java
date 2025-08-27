package khc.arranged.dependency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.arranged.dependency.domain.SampleEntity;
import khc.arranged.dependency.repository.SampleRepository;

@Service
public class SampleService {
	
	@Autowired
	private SampleRepository sampleRepository;
	
	// 기능 메소드 작성
	public void sampleMethod(String username, String userpass) {
		System.out.println("8. 서비스의 기능 메소드 작성");
		// 9. Entity 객체 생성
		SampleEntity sampleEntity = new SampleEntity(username, userpass);
		// 10. Entity 객체를 Repository에 save
		sampleRepository.save(sampleEntity);
	}

	// 목록을 조회하고 반환하는 메소드
	public List<SampleEntity> listMethod() {
		// 5. Repository에서 SampleEntity 전체 목록 조회
		List<SampleEntity> entityList = sampleRepository.findAll();
		// 6. 조회된 SampleEntity 전체 목록 반환
		return entityList;
	}

	public List<SampleEntity> searchMethod(String searchName) {
		return sampleRepository.findByUsername(searchName);
	}
	
}
