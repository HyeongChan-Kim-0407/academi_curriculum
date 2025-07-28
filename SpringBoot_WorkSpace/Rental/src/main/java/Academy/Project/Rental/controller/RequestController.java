package Academy.Project.Rental.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Academy.Project.Rental.dto.RequestAcceptDto;
import Academy.Project.Rental.dto.RequestDto;
import Academy.Project.Rental.dto.RequestForm;
import Academy.Project.Rental.service.RequestService;

@Controller
@RequestMapping("/request")
public class RequestController {

	@Autowired
	private RequestService requestService;

	@PostMapping("/requestOrder/{id}")
	public String requestOrder(@PathVariable("id") Long id, RequestForm requestForm, RedirectAttributes ra) {

		try {
			requestService.requestOrder(id, requestForm);
		} catch (Exception e) {
			System.out.println("대여 요청 실패: " + e.getMessage());
			e.printStackTrace();
			ra.addFlashAttribute("msg", "대여 요청에 실패했습니다. 다시 시도해주세요.");
			return "redirect:/place/view/" + id;
		}
		ra.addFlashAttribute("msg", "대여 요청이 성공적으로 등록되었습니다.");
		return "redirect:/place/view/" + id;
	}

	@GetMapping("/acceptOrder")
	public String acceptOrder(@RequestParam("rId") Long rId, RedirectAttributes ra) {

		RequestDto rd = requestService.findById(rId);

		Long pId = rd.getPlaceId();

		try {
			requestService.acceptOrder(rId);
		} catch (Exception e) {
			System.out.println("대여 요청 수락 실패: " + e.getMessage());
			e.printStackTrace();
			ra.addFlashAttribute("msg", "대여 요청 수락에 실패했습니다. 다시 시도해주세요.");
			return "redirect:/place/view/" + pId;
		}

		ra.addFlashAttribute("msg", "대여 요청이 승인되었습니다.");

		return "redirect:/place/view" + pId;
	}

	@GetMapping("/rejectOrder")
	public String rejectOrder(@RequestParam("rId") Long rId, RedirectAttributes ra) {

		RequestDto rd = requestService.findById(rId);

		Long pId = rd.getPlaceId();
		try {
			requestService.rejectOrder(rId);
		} catch (Exception e) {
			System.out.println("대여 요청 거절 실패: " + e.getMessage());
			e.printStackTrace();
			ra.addFlashAttribute("msg", "대여 요청 거절에 실패했습니다. 다시 시도해주세요.");
			return "redirect:/place/view/" + pId;
		}
		ra.addFlashAttribute("msg", "대여 요청이 거절되었습니다.");
		return "redirect:/place/view" + pId;
	}

	@GetMapping("/getBdate")
	@ResponseBody
	public ArrayList<RequestAcceptDto> getBdate(@RequestParam("bdate") LocalDate bdate, @RequestParam("pId") Long pId) {
		System.out.println("getBdate 호출");
		ArrayList<RequestAcceptDto> raList = requestService.findByBdateAndPid(bdate, pId);

		return raList;
	}

}
