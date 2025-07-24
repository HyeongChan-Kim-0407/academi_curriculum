package Academy.Project.Rental.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.dto.PlaceDto;
import Academy.Project.Rental.dto.PlaceForm;
import Academy.Project.Rental.repository.MemberRepository;
import Academy.Project.Rental.repository.PlaceRepository;
import jakarta.validation.Valid;

@Service
public class PlaceService {

	@Autowired
	private MemberRepository memberrepository;

	@Autowired
	private PlaceRepository placerepository;

	public void regist(@Valid PlaceForm placeform, String Mid) {

		Member member = memberrepository.findByMid(Mid);

		System.out.println("Member : " + member);
		Place place = new Place(placeform, member);

		placerepository.save(place);
	}

	public List<PlaceDto> findPlaceList() {

		List<Place> place = placerepository.findAll();

		List<PlaceDto> pdList = new ArrayList<>();
		for (Place p : place) {
			PlaceDto pd = new PlaceDto(p);
			pdList.add(pd);
		}

		return pdList;
	}

}
