package Academy.Project.Rental.domain;

import java.util.ArrayList;
import java.util.List;

import Academy.Project.Rental.dto.PlaceForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Place {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member pmember; // 장소를 등록한 회원 정보
	@Column(nullable = false)
	private String ptitle; // 장소 이름
	private String pinfo; // 장소 정보
	@Column(nullable = false)
	private String ptype; // 장소 유형 (예: 파티룸, 연습실 등)
	@Column(nullable = false)
	private String plocation; // 장소 위치

	private int pprice; // 대여 가격 (시간당?일당?)

	@OneToMany(mappedBy = "place", cascade = CascadeType.ALL) // cascade = CascadeType.ALL : 연관된 Entity도 함께 save
	private List<PlaceImage> imageList = new ArrayList<>();
	
	@OneToMany(mappedBy = "place")
	private List<Request> requestList = new ArrayList<>(); // 대여 요청 목록

	public Place() {

	}

	public Place(PlaceForm placeform, Member member) {
		this.ptitle = placeform.getPtitle();
		this.pinfo = placeform.getPinfo();
		this.ptype = placeform.getPtype();
		this.plocation = placeform.getPlocation();
		this.pprice = placeform.getPprice();
		this.pmember = member;
	}
}
