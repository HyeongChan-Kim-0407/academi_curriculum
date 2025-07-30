package Academy.Project.Rental.service;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.domain.Reply;
import Academy.Project.Rental.domain.ReplyImage;
import Academy.Project.Rental.domain.RequestAccept;
import Academy.Project.Rental.dto.ReplyDto;
import Academy.Project.Rental.dto.ReplyForm;
import Academy.Project.Rental.repository.MemberRepository;
import Academy.Project.Rental.repository.PlaceRepository;
import Academy.Project.Rental.repository.ReplyRepository;
import Academy.Project.Rental.repository.RequestAcceptRepository;
import Academy.Project.Rental.repository.RequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final RequestRepository requestRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;
    private final RequestAcceptRepository requestAcceptRepository;

    private static final String SAVE_PATH = "C:/reviewImages"; 

    @Transactional
    public void writeReply(ReplyForm form, Long placeId, String mid) throws IOException {
        Member member = memberRepository.findByMid(mid);       
        Place place = placeRepository.findById(placeId)
            .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다."));
        RequestAccept ra = requestAcceptRepository.findByPlaceAndMember(place, member);
        Reply reply = new Reply();
        reply.setMember(member);
        reply.setPlace(place);
        reply.setRcontent(form.getRcontent());
        reply.setRdate(LocalDateTime.now().toString());
        ra.setReplyStatus("작성완료");
        for (MultipartFile file : form.getRfiles()) {
            if (!file.isEmpty()) {
                String original = file.getOriginalFilename();
                String ext = original.substring(original.lastIndexOf("."));
                String newName = UUID.randomUUID() + ext;

                File dest = new File(SAVE_PATH, newName);
                file.transferTo(dest);

                ReplyImage image = new ReplyImage();
                image.setRfilename("/reviewImages/" + newName);
                image.setReply(reply);
                reply.getImageList().add(image);
            }
        }

        replyRepository.save(reply);
    }

    public List<ReplyDto> findReviewById(Long id) {
		Place place = placeRepository.findById(id).orElse(null);
		
		List<Reply> reply = replyRepository.findByPlace(place);
		List<ReplyDto> replyDto = new ArrayList<>();
		
		for (Reply rep : reply) {
			ReplyDto replydto = new ReplyDto(rep);
			replyDto.add(replydto);
		}
		
		return replyDto;
	}
}

















