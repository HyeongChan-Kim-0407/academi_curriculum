package khc.springboot.project6.newsCrawllingProject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import khc.springboot.project6.newsCrawllingProject.dto.ScrapeNewsDto;
import lombok.Getter;

@Entity
@Getter
public class ScrapeNews {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String imgUrl;
	
	@Column(unique = true)
	private String linkUrl;
	
	private String section; // 정치 : 100, 경제 : 101...
	
	private String newsTime;
	
	@Column(length = 10000) // 긴 뉴스 내용 저장을 위해 길이 지정
	private String newsContent;
	
	public ScrapeNews() {
		
	}
	
	public ScrapeNews(ScrapeNewsDto scrapeNewsDto) {
		this.title = scrapeNewsDto.getTitle();
		this.imgUrl = scrapeNewsDto.getImgUrl();
		this.linkUrl = scrapeNewsDto.getLinkUrl();
		this.section = scrapeNewsDto.getSection();
		this.newsTime = scrapeNewsDto.getNewsTime();
		this.newsContent = scrapeNewsDto.getNewsContent();
	}
	
}
