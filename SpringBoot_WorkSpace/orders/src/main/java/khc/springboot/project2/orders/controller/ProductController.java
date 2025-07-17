package khc.springboot.project2.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.Session;
import khc.springboot.project2.orders.dto.ProductForm;
import khc.springboot.project2.orders.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ProductService productsvc;
	
	@GetMapping("/regist")
	public String productRegistPage(Model model) {
		System.out.println("/product/regist(get) - 상품 등록 페이지 이동 요청");
		ProductForm productForm = new ProductForm();
		model.addAttribute("productForm", productForm);
		return "product/registProductForm";
	}
	
	@PostMapping("/regist")
	public String productRegist(@Valid ProductForm productForm, AbstractBindingResult bindingResult, Model model, RedirectAttributes ra) {
		System.out.println("/product/regist(post) - 상품 등록 요청");
		
		if(bindingResult.hasErrors()) {
			System.out.println("유효성 검사 실패");
			model.addAttribute("productForm", productForm);
			return "product/registProductForm";
		}
		
		String loginId = (String) session.getAttribute("loginId");
		
		try {
			productsvc.registerProduct(productForm, loginId);
			ra.addFlashAttribute("Msg", "상품 등록이 완료되었습니다.");
		}catch(Exception e) {
			System.out.println("상품 등록 실패: " + e.getMessage());
			ra.addFlashAttribute("Msg", "상품 등록에 실패하였습니다.");
		}
		ra.addFlashAttribute("msg", "상품 등록이 완료되었습니다.");
		return "redirect:/";
	}
	
	
}
