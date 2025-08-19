package khc.springboot.project6.newsCrawllingProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project6.newsCrawllingProject.domain.ScrapeNews;
import khc.springboot.project6.newsCrawllingProject.dto.ScrapeNewsDto;
import khc.springboot.project6.newsCrawllingProject.repository.NewsRepository;

@Service
public class NewsService {
	
	@Autowired
	private JsoupService jsoupService;
	
	@Autowired
	private NewsRepository newsRepository;
	
	public void findNewsList() {
		// 네이버 뉴스 카테고리 하나 선정
		// 뉴스 목록 수집
		List<ScrapeNews> newsList = new ArrayList<>();
		List<ScrapeNewsDto> dtoList = jsoupService.scrapeNaverNews();
		
		for(ScrapeNewsDto dto : dtoList) {
			String link = dto.getLinkUrl();
			ScrapeNews savedNews = newsRepository.findByLinkUrl(link);
			if(savedNews == null) {
			ScrapeNews news = new ScrapeNews(dto);
			newsList.add(news);
			}
		}
		
		newsRepository.saveAll(newsList);
		System.out.println("기사 수 : " + newsList.size());
		
	}
	
}
