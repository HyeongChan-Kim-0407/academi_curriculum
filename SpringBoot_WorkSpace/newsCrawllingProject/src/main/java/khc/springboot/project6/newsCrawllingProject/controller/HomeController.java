package khc.springboot.project6.newsCrawllingProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import khc.springboot.project6.newsCrawllingProject.service.JsoupService;
import khc.springboot.project6.newsCrawllingProject.service.NewsService;

@Controller
public class HomeController {
	
	@Autowired
	private JsoupService jsoupService;
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/")
	public String home() {
//		newsService.findNewsList();
		return "home";
	}
	
}
