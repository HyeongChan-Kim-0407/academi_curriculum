package khc.springboot.project6.newsCrawllingProject.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project6.newsCrawllingProject.dto.ScrapeNewsDto;
import khc.springboot.project6.newsCrawllingProject.repository.NewsRepository;

@Service
public class JsoupService {
	
	@Autowired
	private NewsRepository newsRepository;

	public List<ScrapeNewsDto> scrapeNaverNews(String section, String portal) {
		List<ScrapeNewsDto> scrapeList = new ArrayList<>();
		// 1. 수집할 페이지 선정
		String url = "https://news.naver.com/" + section;
		
		try {
			// 2. URL 요청 >> HTML 문서 응답
			Document doc = Jsoup.connect(url).get();
			// 3. 수집할 데이터가 있는 요소 선택
			// 선택자 : #_SECTION_HEADLINE_LIST_ecybi
			Element headLineUl = doc.select("#newsct").first();
			//상세 페이지 URL만 수집하여 List에 저장
			List<String> linkUrlList = new ArrayList<>();
			Elements headLineList = headLineUl.select("li.sa_item");
			for(Element headLine : headLineList) {
				
				String linkUrl = headLine.select("a.sa_text_title").attr("href");
				linkUrlList.add(linkUrl);
				
			}
			System.out.println("문서 수 : " + headLineList.size());
			// 4. 중복되지 않는 상세 페이지 URL만 선별
			// Repository에서 저장된 linkUrl 목록 조회
			List<String> savedList = newsRepository.findAllNaverLinkUrl();
			linkUrlList.removeAll(savedList);
			// 5. 선별된 URL로 상세페이지 접속 및 데이터 수집
			for(String linkUrl : linkUrlList) {
				ScrapeNewsDto dto = new ScrapeNewsDto();
				
				Document detailDoc = Jsoup.connect(linkUrl).get();
				
				String titleEl = detailDoc.select("#title_area > span").text();
				dto.setTitle(titleEl);
				
				String imgUrl = detailDoc.select("#img1").attr("data-src");
				dto.setImgUrl(imgUrl);
				
				Element detailCt = detailDoc.select("#ct").first();
				
				String newsTime = detailCt.select("#ct > div.media_end_head.go_trans > div.media_end_head_info.nv_notrans > div.media_end_head_info_datestamp > div:nth-child(1) > span").text();
				dto.setNewsTime(newsTime);
				
				detailCt.select("span").remove();
				String newsContent = detailCt.select("#dic_area").text();
				dto.setNewsContent(newsContent);
				
				String journal = detailCt.select("#ct > div.media_end_head.go_trans > div.media_end_head_top._LAZY_LOADING_WRAP > a > img.media_end_head_top_logo_img.light_type._LAZY_LOADING._LAZY_LOADING_INIT_HIDE._LAZY_LOADING_ERROR_HIDE").attr("title");
				dto.setJournal(journal);
				
				//상세 페이지 URL에서 언론사 분류 코드 추출 후 저장 (article 이후에 추출 or 뒤에서부터 추출)
				//String journal
				
				// 
				
				dto.setLinkUrl(linkUrl);
				dto.setSection(section);
				
				dto.setPortal(portal);
				
				
				
				scrapeList.add(dto);
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return scrapeList;
	}
	
	public List<ScrapeNewsDto> scrapeDaumNews(String section, String portal) {
		List<ScrapeNewsDto> scrapeList = new ArrayList<>();
		String url = "https://news.daum.net/" + section;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			Element headLineUl = doc.select("#mainContent > article > div").first();
			
			List<String> linkUrlList = new ArrayList<>();
			
			Elements headLineList = headLineUl.select("div.box_comp > ul > li");
			for(Element headLine : headLineList) {
				String linkUrl = headLine.select("a").attr("href");
				linkUrlList.add(linkUrl);
			}
			List<String> savedList = newsRepository.findAllDaumLinkUrl();
			linkUrlList.removeAll(savedList);
			for(String linkUrl : linkUrlList) {
				ScrapeNewsDto dto = new ScrapeNewsDto();
				
				Document detailDoc = Jsoup.connect(linkUrl).get();
				
				Element detailCt = detailDoc.select("#mArticle").first();
				
				String titleEl = detailCt.select("div.head_view > h3").text();
				dto.setTitle(titleEl);
				
				String imgUrl = detailCt.select("p.link_figure > img").attr("data-org-src");
				dto.setImgUrl(imgUrl);
				
				String newsTime = detailCt.select("span.num_date").text();
				dto.setNewsTime(newsTime);
				
				Element sectionEl = detailCt.select("div.news_view.fs_type1 > div.article_view > section").first();
				Elements pTags = sectionEl.select("p");
				
				StringBuilder contentBuilder = new StringBuilder();
				for (Element p : pTags) {
					contentBuilder.append(p.text()).append("\n");
				}				
				String newsContent = contentBuilder.toString().trim();
				dto.setNewsContent(newsContent);
				
				String journal = detailDoc.select("#kakaoServiceLogo").text();
				dto.setJournal(journal);
				
				//상세 페이지 URL에서 언론사 분류 코드 추출 후 저장 (article 이후에 추출 or 뒤에서부터 추출)
				//String journal
				
				// 
				
				dto.setLinkUrl(linkUrl);
				dto.setSection(section);
				
				dto.setPortal(portal);
				
				
				
				scrapeList.add(dto);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return scrapeList;
	}
		
}
