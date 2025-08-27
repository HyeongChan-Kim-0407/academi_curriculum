package khc.arranged.dependency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.arranged.dependency.domain.EntityClass;
import khc.arranged.dependency.repository.EntityRepository;

@Service
public class ResourceService {
	
	@Autowired
	private EntityRepository entityRepository;
	
	public List<EntityClass> getDataList(String pname) {
		// 테이블에서 select * from 테이블 where name = ? 처리
		List<EntityClass> ecList = entityRepository.findByName(pname);
		// 조회결과 반환
		return ecList;
	}
	
	
}
