package my.spring.springweb.sample01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testController02")
public class TestController02 {

	private static final Logger logger = 
			LoggerFactory.getLogger(TestController02.class);
	
	@RequestMapping("/info")
	String myMethod01() {
		logger.debug("[GET] testController02/info");
		return "sample01/testController02";
	}
	
	@RequestMapping("/profile")
	String myMethod02() {
		logger.debug("[GET] testController02/profile");
		return "sample01/testController02";
	}
	
	@RequestMapping(value = {"", "test01", "test02", "test03/*"})
	// /testController02, /testController02/test01, /testController02/test02, /testController02/test03/*
	String myMethod03() {
		logger.debug("[GET] testController02 다중 URL 맵핑");
		return "sample01/testController02";
	}
}
