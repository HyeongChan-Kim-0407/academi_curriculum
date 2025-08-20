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
	
	public int findNewsList(String sectionNumber) {
		// 네이버 뉴스 카테고리 하나 선정
		// 뉴스 목록 수집
		List<ScrapeNews> newsList = new ArrayList<>();
		List<ScrapeNewsDto> dtoList = jsoupService.scrapeNaverNews(sectionNumber);
		
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
		
		return newsList.size();
	}

	public List<ScrapeNewsDto> getNewsBySection(String sectionNumber) {
		
		List<ScrapeNews> newsList = newsRepository.findBySection(sectionNumber);
		List<ScrapeNewsDto> dtoList = new ArrayList<>();
		if(newsList != null) {
			for(ScrapeNews news : newsList) {
				ScrapeNewsDto dto = new ScrapeNewsDto(news);
				dtoList.add(dto);
			}
		}else {
			System.out.println("해당 섹션의 뉴스가 없습니다.");
			return null;
		}
		
		return dtoList;
	}
	
}
