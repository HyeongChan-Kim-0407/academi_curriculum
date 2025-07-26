package Academy.Project.Rental.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.domain.Request;
import Academy.Project.Rental.domain.RequestAccept;
import Academy.Project.Rental.dto.RequestDto;
import Academy.Project.Rental.dto.RequestForm;
import Academy.Project.Rental.repository.MemberRepository;
import Academy.Project.Rental.repository.PlaceRepository;
import Academy.Project.Rental.repository.RequestAcceptRepository;
import Academy.Project.Rental.repository.RequestRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class RequestService {

	@Autowired
	private HttpSession session;

	@Autowired
	private MemberRepository memberrepository;

	@Autowired
	private PlaceRepository placerepository;

	@Autowired
	private RequestRepository requestrepository;
	
	@Autowired
	private RequestAcceptRepository requestAcceptRepository;

	public RequestDto findById(Long id) {

		Request request = requestrepository.findById(id).orElse(null);

		RequestDto rd = new RequestDto(request);

		return rd;
	}

	public void requestOrder(Long id, RequestForm requestForm) {

		String loginMid = (String) session.getAttribute("loginMid");

		Member member = memberrepository.findByMid(loginMid);

		if (requestForm.getR11_13() == null || requestForm.getR11_13().isEmpty()) {
			requestForm.setR11_13("X");
		}
		if (requestForm.getR13_15() == null || requestForm.getR13_15().isEmpty()) {
			requestForm.setR13_15("X");
		}
		if (requestForm.getR15_17() == null || requestForm.getR15_17().isEmpty()) {
			requestForm.setR15_17("X");
		}
		if (requestForm.getR17_19() == null || requestForm.getR17_19().isEmpty()) {
			requestForm.setR17_19("X");
		}
		if (requestForm.getR19_21() == null || requestForm.getR19_21().isEmpty()) {
			requestForm.setR19_21("X");
		}
		if (requestForm.getR21_23() == null || requestForm.getR21_23().isEmpty()) {
			requestForm.setR21_23("X");
		}

		Place place = placerepository.findById(id).orElse(null);

		if (member == null) {
			throw new IllegalArgumentException("로그인된 사용자가 없습니다.");
		}

		Request request = new Request(requestForm, member, place);

		requestrepository.save(request);

	}

	public List<RequestDto> findRequestByPlaceId(Long id) {

		Place place = placerepository.findById(id).orElse(null);

		List<Request> requestList = requestrepository.findByPlace(place);

		List<RequestDto> rdList = new ArrayList<>();

		for (Request r : requestList) {
			RequestDto rd = new RequestDto(r);
			rdList.add(rd);
		}

		return rdList;
	}

	public void acceptOrder(Long rId) {
		
		Request request = requestrepository.findById(rId).orElse(null);
		
		RequestAccept raccept = new RequestAccept(request);
		
		requestAcceptRepository.save(raccept);
		
		request.setRstatus("승인됨");
		requestrepository.save(request);

	}

	public void rejectOrder(Long rId) {
		
		Request request = requestrepository.findById(rId).orElse(null);
		
		request.setRstatus("거절됨");
		requestrepository.save(request);
		
	}

}
