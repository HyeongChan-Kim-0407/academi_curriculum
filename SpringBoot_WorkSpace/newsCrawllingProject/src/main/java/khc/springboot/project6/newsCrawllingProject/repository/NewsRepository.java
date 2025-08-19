package khc.springboot.project6.newsCrawllingProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project6.newsCrawllingProject.domain.ScrapeNews;

public interface NewsRepository extends JpaRepository<ScrapeNews, Long> {

	ScrapeNews findByLinkUrl(String linkUrl);
	
	
	
}
