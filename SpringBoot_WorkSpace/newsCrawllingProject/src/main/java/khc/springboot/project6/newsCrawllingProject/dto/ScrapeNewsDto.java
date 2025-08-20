package khc.springboot.project6.newsCrawllingProject.dto;

import khc.springboot.project6.newsCrawllingProject.domain.ScrapeNews;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ScrapeNewsDto {
	private String title;
	private String imgUrl;
	private String linkUrl;
	private String section;
	private String newsTime;
	private String newsContent;
	public ScrapeNewsDto() {
		// 기본 생성자
	}
	public ScrapeNewsDto(ScrapeNews scrapeNews) {
		this.title = scrapeNews.getTitle();
		this.imgUrl = scrapeNews.getImgUrl();
		this.linkUrl = scrapeNews.getLinkUrl();
		this.section = scrapeNews.getSection();
		this.newsTime = scrapeNews.getNewsTime();
		this.newsContent = scrapeNews.getNewsContent();
	}
}
