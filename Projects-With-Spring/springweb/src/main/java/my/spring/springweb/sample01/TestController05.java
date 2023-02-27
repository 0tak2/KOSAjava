package my.spring.springweb.sample01;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.spring.springweb.HomeController;

@Controller
@RequestMapping(value="/testController05")
public class TestController05 {
	
//	private static final Logger logger =
//			LoggerFactory.getLogger(TestController05.class);
	
//	@GetMapping
//	String myMethod1() {
//		logger.debug("TestController05 Listening to a GET request...");
//		return "sample01/testController05";
//	}
	
	@GetMapping(params="myName") // 파라미터 중 myName이라는 파라미터가 있으면 호출
	String myMethod2() {
//		logger.debug("TestController05 Listening to a GET request with myName param...");
		return "sample01/testController05";
	}
	
	@GetMapping(params="myName=신사임당") // 파라미터 중 myName이라는 파라미터가 있으면 호출
	String myMethod3() {
//		logger.debug("TestController05 Listening to a GET request with myName param=신사임당...");
		return "sample01/testController05";
	}
}
