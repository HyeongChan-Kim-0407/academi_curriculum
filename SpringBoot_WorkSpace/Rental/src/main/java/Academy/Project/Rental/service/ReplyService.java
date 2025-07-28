package Academy.Project.Rental.service;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.domain.Reply;
import Academy.Project.Rental.domain.ReplyImage;
import Academy.Project.Rental.domain.Request;
import Academy.Project.Rental.dto.ReplyDto.ReplyForm;
import Academy.Project.Rental.repository.MemberRepository;
import Academy.Project.Rental.repository.ReplyRepository;
import Academy.Project.Rental.repository.RequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final RequestRepository requestRepository;
    private final MemberRepository memberRepository;

    private static final String SAVE_PATH = "C:/reviewImages"; 

    @Transactional
    public void writeReply(ReplyForm form, Long requestId, String mid) throws IOException {
        Member member = memberRepository.findByMid(mid);
        Request request = requestRepository.findById(requestId)
            .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다."));

        Reply reply = new Reply();
        reply.setMember(member);
        reply.setRequest(request);
        reply.setRcontent(form.getRcontent());
        reply.setRdate(LocalDateTime.now().toString());

        for (MultipartFile file : form.getRfiles()) {
            if (!file.isEmpty()) {
                String original = file.getOriginalFilename();
                String ext = original.substring(original.lastIndexOf("."));
                String newName = UUID.randomUUID() + ext;

                File dest = new File(SAVE_PATH, newName);
                file.transferTo(dest);

                ReplyImage image = new ReplyImage();
                image.setRfilename("/uploads/" + newName);
                image.setReply(reply);
                reply.getImageList().add(image);
            }
        }

        replyRepository.save(reply);
    }
}