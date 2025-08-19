package khc.springboot.project6.newsCrawllingProject.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import khc.springboot.project6.newsCrawllingProject.dto.ScrapeNewsDto;

@Service
public class JsoupService {
	
	public void JsoupSample() throws IOException {
		Document doc = Jsoup.connect("https://news.naver.com/section/100").get();
		System.out.println(doc);
		System.out.println(doc.title());
		
	}

	public List<ScrapeNewsDto> scrapeNaverNews() {
		List<ScrapeNewsDto> scrapeList = new ArrayList<>();
		// 1. 수집할 페이지 선정
		String url = "https://news.naver.com/section/100";
		
		try {
			// 2. URL 요청 >> HTML 문서 응답
			Document doc = Jsoup.connect(url).get();
			// 3. 수집할 데이터가 있는 요소 선택
			// 선택자 : #_SECTION_HEADLINE_LIST_ecybi
			Element headLineUl = doc.select("#newsct").first();
			
			Elements headLineList = headLineUl.select("li.sa_item");
			for(Element headLine : headLineList) {
				ScrapeNewsDto dto = new ScrapeNewsDto();
				String titleEl = headLine.select("strong.sa_text_strong").text();
				dto.setTitle(titleEl);
				
				String imgUrl = headLine.select("div.sa_thumb > div > a > img").attr("data-src");
				dto.setImgUrl(imgUrl);
				
				String linkUrl = headLine.select("a.sa_text_title").attr("href");
				dto.setLinkUrl(linkUrl);
				
				scrapeList.add(dto);
				
			}
			System.out.println("문서 수 : " + headLineList.size());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return scrapeList;
	}
	
}
