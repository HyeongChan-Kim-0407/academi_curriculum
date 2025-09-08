package khc.springboot.project7.total.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project7.total.domain.Location;
import khc.springboot.project7.total.domain.Member;
import khc.springboot.project7.total.dto.LocationDto;
import khc.springboot.project7.total.dto.MemberDto;
import khc.springboot.project7.total.dto.MemberForm;
import khc.springboot.project7.total.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void memgerJoin(MemberForm memberForm) throws Exception {
		System.out.println("MemberService.memberJoin() 호출");
		memberForm.setMjoindate(LocalDate.now());
		memberForm.setMroute("홈페이지");
		System.out.println("memberForm: " + memberForm);
		
		Member member = new Member(memberForm);
		memberRepository.save(member);
		
	}

	public String checkId(String mid) {
		System.out.println("MemberService.checkId() 호출");
		Member member = memberRepository.findByMid(mid);
		if(member != null) {
			System.out.println("중복된 아이디가 존재합니다.");
			return "N";
		}		
		return "Y";
	}

	public MemberDto memberLogin(String mid, String mpw) {
		System.out.println("MemberService.memberLogin() 호출");
		Member member = memberRepository.findByMidAndMpw(mid, mpw);
		if(member != null) {
			MemberDto memberDto = new MemberDto(member);
			return memberDto;
		}
		
		return null;
	}

	public void updateMemberLocations(List<String> locationNames, MemberDto loginUser) {
		System.out.println("MemberService.updateMemberLocations() 호출");
		String loginId = loginUser.getMid();
		List<Location> locations = new ArrayList<>();
		List<LocationDto> dtoList = new ArrayList<>();
		for(String loc : locationNames) {
			LocationDto locationDto = new LocationDto();
			if(loc.equals("서울")) {
				locationDto.setLocationName("Seoul");
				locationDto.setNx("61");
				locationDto.setNy("125");
				locationDto.setMid(loginId);
			}else if(loc.equals("인천")) {
				locationDto.setLocationName("Incheon");
				locationDto.setNx("54");
				locationDto.setNy("125");
				locationDto.setMid(loginId);
			}else if(loc.equals("부천")) {
				locationDto.setLocationName("Bucheon");
				locationDto.setNx("57");
				locationDto.setNy("125");
				locationDto.setMid(loginId);
			}
			dtoList.add(locationDto);
		}
		
		if(dtoList.size() > 0) {
			for(LocationDto dto : dtoList) {
				String mid = dto.getMid();
				Member member = memberRepository.findByMid(mid);
				Location loc = new Location(dto, member);
				locations.add(loc);
			}
		}
		
		Member member = new Member(loginUser, locations);
		memberRepository.save(member);
		
	}

}
