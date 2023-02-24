package my.spring.springweb.sample01;

import org.slf4j.Logger; // 1버전대 인터페이스. 요걸로 잡는다. 
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController01 {

	private static final Logger logger = 
			LoggerFactory.getLogger(TestController01.class); // 로거 사용
	
	// http://localhost:8080/springweb + 뒤에 나올 꺼
	// http://localhost:8080/springweb/testController01
	@RequestMapping(value="/testController01", method=RequestMethod.GET) // '/' 생략 가능
	String myMethod() {
		logger.debug("[GET] /springweb/testController01"); // 원하는 레벨의 메서드 호출
		
		return "sample01/testController01";
	}
}
