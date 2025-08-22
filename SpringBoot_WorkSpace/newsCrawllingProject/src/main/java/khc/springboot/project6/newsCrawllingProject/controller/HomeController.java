package khc.springboot.project6.newsCrawllingProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khc.springboot.project6.newsCrawllingProject.dto.ScrapeNewsDto;
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
	
	@GetMapping("/scrapeNews")
	@ResponseBody
	public String scrapeNews(@RequestParam("section") String section, @RequestParam("portal") String portal) {
		// 정치 : 100, 경제 : 101, 사회 : 102, 생활/문화 : 103, 세계 : 104, IT/과학 : 105
		int result = newsService.findNewsList(section, portal);
		return result + "개의 뉴스가 수집되었습니다.";
	}
	
	@GetMapping("/showNews")
	@ResponseBody
	public List<ScrapeNewsDto> showNews(@RequestParam("section") String sectionNumber, @RequestParam("portal") String portal){
		System.out.println("뉴스 조회 기능 호출");
		List<ScrapeNewsDto> newsList = newsService.getNewsBySection(sectionNumber, portal);
		
		if(newsList == null) {
			System.out.println("해당 섹션의 뉴스가 없습니다.");
			return null;
		}
		
		return newsList;
	}
	
	
	
}
