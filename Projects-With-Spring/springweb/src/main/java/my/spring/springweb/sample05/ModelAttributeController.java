package my.spring.springweb.sample05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import my.spring.springweb.sample05.vo.User;

@Controller
public class ModelAttributeController {

	Logger log = LogManager.getLogger("case3");
	
	// 이름을 명시해줘야 함.
	// 해당 클래스 내의 핸들러가 실행되기 전에 이 메서드가 자동으로 호출됨
	// 리턴 값이 Model에 자동으로 등록됨
	@ModelAttribute("v1")
	public String createString() {
		log.debug("문자열 객체 생성");
		return "이것은 소리 없는 아우성";
	}
	
	@ModelAttribute("v2")
	public User createUser() {
		log.debug("User 객체 생성");
		User user = new User(25, "홍길동", "철학과");
		return user;
	}
	
	@ModelAttribute("data1")
	public int createNumber1() {
		log.debug("숫자 1 생성");
		return 100;
	}
	
	@ModelAttribute("data2")
	public int createNumber2() {
		log.debug("숫자 2 생성");
		return 200;
	}
	
	@RequestMapping(value = "/modelAttributes1")
	public String myMethod1(@ModelAttribute("data1") int num1, // VO로 Request Body를 가져온 것이 아니라, 위의 메서드의 리턴값이 주입
			@ModelAttribute("data2") int num2,
			Model model) {
		log.debug("Handler Invoked");
		
		model.addAttribute("sum", num1 + num2);
		return "sample05/modelResult";
	}
}
