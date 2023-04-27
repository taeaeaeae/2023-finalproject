package org.zerock.myapp.mapper;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.domain.BookmarkDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/**/spring/root-*.xml",
								   "file:src/main/webapp/**/spring/**/servlet-*.xml"})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookmarkMapperTest {
	
	@Setter(onMethod_ = {@Autowired})
	private BookmarkMapper mapper; 
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(this.mapper);
		log.info("\t+ mapper : {}", this.mapper);
	} // beforeAll
	
//	@Disabled
	@Order(1)
	@Test
	@DisplayName("test : bookmark mapper test")
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void addBookmarkTests() {
		log.trace("addBookmarkTests() invoked.");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUids("bbbb");
		dto.setFid(239);
		
		Integer affectedLines = this.mapper.addBookmark(dto);
		log.trace("\t+ affectedLines : {}", affectedLines);
	} // contextLoads
	
//	@Disabled
	@Order(2)
	@Test
	@DisplayName("test : bookmark mapper remove test")
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void removeBookmarkTests() {
		log.trace("removeBookmarkTests() invoked.");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUids("bbbb");
		dto.setFid(239);
		
		Integer affectedLines = this.mapper.removeBookmark(dto);
		log.trace("\t+ affectedLines : {}", affectedLines);
	} // removeBookmarkTests
	
//	@Disabled
	@Order(3)
	@Test
	@DisplayName("test : bookmark mapper remove test")
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void isBookmarkedTest() {
		log.trace("isBookmarkedTest() invoked.");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUids("cccc");
		dto.setFid(239);
		
		Integer affectedLines = this.mapper.isBookmarked(dto);
		log.trace("\t+ affectedLines : {}", affectedLines);
	} // removeBookmarkTests

} // end class