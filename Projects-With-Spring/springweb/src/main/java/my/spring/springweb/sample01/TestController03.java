package my.spring.springweb.sample01;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/testController03")
public class TestController03 {

//	private static final Logger logger = 
//			LoggerFactory.getLogger(TestController03.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET) // 같은 URL, 다른 메서드
	String myMethod1() {
		return null;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	String myMethod2() {
		return null;
	}
}
