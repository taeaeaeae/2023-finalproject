package org.zerock.myapp.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data
public class Criteria {
	private Integer currPage = 1;		
	private Integer amount =  10;		
	
	private Integer pagesPerPage = 10;	
	
	private String type;
	private String keyword;
	
	public String getPagingUri() {
		log.trace("getPagingUri() invoked.");
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
		builder.queryParam("currPage", this.currPage);
		builder.queryParam("amount", this.amount);
		builder.queryParam("pagesPerPage", this.pagesPerPage);
		builder.queryParam("type", this.type);
		builder.queryParam("keyword", this.keyword);
		
		String queryString = builder.toUriString();
		log.info("\t+ queryString: {}", queryString);
		
		return queryString;
	} // getPagingUri
	
} // end class
