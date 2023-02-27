package my.spring.springweb.sample01;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

@Controller
@RequestMapping(value="testController06")
public class TestController06 {
	
//	private static final Logger logger =
//			LoggerFactory.getLogger(TestController06.class);
	
	public TestController06() {
//		logger.debug("빈 생성됨");
	}
	
	// HTML
	@GetMapping
	public ModelAndView showStaticView() {
//		logger.debug("TestController06");
		
		// HTML의 경우 webapp 하위부터 절대 경로를 이용하는 것이 편리
		InternalResourceView view = 
				new InternalResourceView("/resources/sample01/testController06.html"); // /resources... '/' 빠지면 안됨
		
		ModelAndView mav = new ModelAndView(view); // 모델은 없으면 안써도 됨. 혹은 선언부에 Model을 명시에서 주입받아도 됨
		
		return mav;
	}
}
