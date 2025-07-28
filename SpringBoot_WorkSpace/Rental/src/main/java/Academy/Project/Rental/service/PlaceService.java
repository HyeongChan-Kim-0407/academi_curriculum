package Academy.Project.Rental.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.domain.PlaceImage;
import Academy.Project.Rental.dto.PlaceDto;
import Academy.Project.Rental.dto.PlaceForm;
import Academy.Project.Rental.repository.MemberRepository;
import Academy.Project.Rental.repository.PlaceRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class PlaceService {

	@Autowired
	private HttpSession session;

	@Autowired
	private MemberRepository memberrepository;

	@Autowired
	private PlaceRepository placerepository;

	private final String SAVEPATH = "C:/project";

	public void regist(PlaceForm placeform, String Mid) throws IllegalStateException, IOException {

		Member member = memberrepository.findByMid(Mid);
		Place place = new Place(placeform, member);

		MultipartFile[] pfiles = placeform.getPfiles();

		for (MultipartFile pfile : pfiles) {
			if (!pfile.isEmpty()) { // 업로드한 파일이 있는 경우
				// 파일을 업로드할 폴더에 저장
				// C:/productImages
				String originalFileName = pfile.getOriginalFilename(); // 업로드한 원본 파일명 (abc.jpg)
				int suffixIndex = originalFileName.lastIndexOf("."); // 확장자가 시작되는 index
				String pfilename = UUID.randomUUID().toString() + originalFileName.substring(suffixIndex);

				File file = new File(SAVEPATH, pfilename);
				pfile.transferTo(file);

				// ProductImage 엔티티 생성
				PlaceImage placeImage = new PlaceImage();
				placeImage.setPfilename("/project/" + pfilename);
				placeImage.setPlace(place);
				// 생성된 productImage 엔티티를 product 엔티티에 imageList에 add()
				place.getImageList().add(placeImage);
			}

		}
		placerepository.save(place); // Place 엔티티 저장
	}

	public List<PlaceDto> findPlaceList(String ptype) {

		List<Place> place = placerepository.findByPtype(ptype);

		List<PlaceDto> pdList = new ArrayList<>();
		for (Place p : place) {
			PlaceDto pd = new PlaceDto(p);
			pdList.add(pd);
		}

		return pdList;
	}

	public PlaceDto findPlaceById(Long id) {

		Place place = placerepository.findById(id).orElse(null);

		if (place == null) {
			return null;
		}

		PlaceDto pd = new PlaceDto(place);

		String loginMid = (String) session.getAttribute("loginMid");

		pd.setMine(pd.getMid().equals(loginMid)); // 현재 로그인한 회원이 장소 등록자인지 여부를 설정

		return pd;
	}

	public List<PlaceDto> findPlaceListAll() {
		List<Place> placeList = placerepository.findAll();
		List<PlaceDto> dtoList = new ArrayList<>();
		for (Place Place : placeList) {
			PlaceDto placeDto = new PlaceDto(Place);
			dtoList.add(placeDto);
		}
		return dtoList;
	}

	public List<PlaceDto> findsearch(String ptitle, String ptype, String plocation) {
		List<Place> placeList = placerepository.findByPtitleContainingAndPtypeContainingAndPlocationContaining(ptitle,
				ptype, plocation);
		List<PlaceDto> dtoList = new ArrayList<>();
		for (Place Place : placeList) {
			PlaceDto placeDto = new PlaceDto(Place);
			dtoList.add(placeDto);
		}
		return dtoList;
	}

}
