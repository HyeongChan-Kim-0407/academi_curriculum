package khc.springboot.project6.newsCrawllingProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import khc.springboot.project6.newsCrawllingProject.domain.ScrapeNews;
import khc.springboot.project6.newsCrawllingProject.dto.ScrapeNewsDto;
import khc.springboot.project6.newsCrawllingProject.repository.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private JsoupService jsoupService;

	@Autowired
	private NewsRepository newsRepository;

	public int findNewsList(String section, String portal) {
		// 네이버 뉴스 카테고리 하나 선정
		// 뉴스 목록 수집
		List<ScrapeNews> newsList = new ArrayList<>();
		List<ScrapeNewsDto> dtoList = new ArrayList<>();
		if (portal.equals("naver")) {
			dtoList = jsoupService.scrapeNaverNews(section, portal);
		}
		if( portal.equals("daum")) {
			dtoList = jsoupService.scrapeDaumNews(section, portal);
		}
		int count = 0;
		for (ScrapeNewsDto dto : dtoList) {
			String link = dto.getLinkUrl();
			ScrapeNews savedNews = newsRepository.findByLinkUrl(link);
			if (savedNews == null) {
				String newsContent = dto.getNewsContent();
				String summary = summaryByGemini(newsContent);
				dto.setNewsContent(summary);
				ScrapeNews news = new ScrapeNews(dto);
				newsList.add(news);
				count++;
				if(count > 9) {
					break;
				}
			}
		}

		newsRepository.saveAll(newsList);
		System.out.println("기사 수 : " + newsList.size());

		return newsList.size();
	}

	public List<ScrapeNewsDto> getNewsBySection(String sectionNumber, String portal) {

		List<ScrapeNews> newsList = newsRepository.findBySectionAndPortal(sectionNumber, portal);
		List<ScrapeNewsDto> dtoList = new ArrayList<>();
		if (newsList != null) {
			for (ScrapeNews news : newsList) {
				ScrapeNewsDto dto = new ScrapeNewsDto(news);
				dtoList.add(dto);
			}
		} else {
			System.out.println("해당 섹션의 뉴스가 없습니다.");
			return null;
		}

		return dtoList;
	}

	private String summaryByGemini(String newsContent) {

		String prompt = "다음에 제공되는 기사내용을 문장이 끝날때마다 엔터키 등으로 구분해서 3줄로 요약해줘."
				+ "요약은 특정 관점 없이 중립적인 문체로 작성하고,"
				+ "주관적인 판단이나 의견은 제외하고 기사 본문에 있는 내용과 사실만을 사용해서 한국어로 요약해줘. \n";

		Client client = Client.builder().apiKey("AIzaSyA8qDx7aAEdFkfBAooB8-sJ3iZBIh8VNyU").build();

		GenerateContentResponse response =
				client.models.generateContent(
					"gemini-2.5-flash",
					prompt + newsContent,
					null);
		
		System.out.println(response.text());

		return response.text();
	}

}
