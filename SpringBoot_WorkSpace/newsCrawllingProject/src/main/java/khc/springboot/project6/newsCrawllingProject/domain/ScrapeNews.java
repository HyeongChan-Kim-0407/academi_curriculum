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
	
	public ScrapeNews() {
		
	}
	
	public ScrapeNews(ScrapeNewsDto scrapeNewsDto) {
		this.title = scrapeNewsDto.getTitle();
		this.imgUrl = scrapeNewsDto.getImgUrl();
		this.linkUrl = scrapeNewsDto.getLinkUrl();
	}
	
}
