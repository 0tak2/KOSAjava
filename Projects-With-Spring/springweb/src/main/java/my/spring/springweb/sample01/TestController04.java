package my.spring.springweb.sample01;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// @RequestMapping은 클래스 레벨에서 사용하는 것이 일반적
@Controller
@RequestMapping(value="testController04")
public class TestController04 {

//	private static final Logger logger = 
//			LoggerFactory.getLogger(TestController04.class);
	
	@GetMapping
	String myMethod01() {
//		logger.debug("TestController04 Listening to a GET request...");
		return "sample01/testController04";
	}
	
	@PostMapping(value="")
	String myMethod02() {
//		logger.debug("TestController04 Listening to a POST request...");
		return "sample01/testController04";
	}
	
	@GetMapping(value="test001")
	String myMethod03() {
//		logger.debug("TestController04 Listening to a GET request... (/test001)");
		return "sample01/testController04";
	}
}
