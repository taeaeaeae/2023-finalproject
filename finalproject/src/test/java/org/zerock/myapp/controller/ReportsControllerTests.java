package org.zerock.myapp.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

@NoArgsConstructor
@Log4j2

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = 
							{"file:src/main/webapp/**/spring/root-*.xml",
							 "file:src/main/webapp/**/spring/**/servlet-*.xml"})

@WebAppConfiguration

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)

public class ReportsControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	private MockMvcBuilder mockMbvBuilder;
	private MockMvc mockMvc;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.ctx);
		log.info("\t+ this.ctx: {}", this.ctx);
		
		this.mockMbvBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		this.mockMvc = mockMbvBuilder.build();
		log.info("\t+ mockMvc : {}", mockMvc);
	} // beforeAll

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("test1 : testRegisterReport")
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testRegisterReport() throws Exception {
		log.trace("testRegisterReport() invoked.");
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/reports/register");
		builder.param("fid", "235");
		builder.param("uids", "gkeirj");
		builder.param("target_board_name", "free_baord");
		builder.param("reason", "비방 및 욕설");
		
		ModelAndView modelAndView = mockMvc.perform(builder).andReturn().getModelAndView();
		log.info("\t+ viewName : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());
	} // testRegister
} // end class