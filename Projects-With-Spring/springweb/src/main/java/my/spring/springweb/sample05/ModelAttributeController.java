package my.spring.springweb.sample05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(value = "/modelAttributes2") // POST 메서드 대응
	public String myMethod2(/* @ModelAttribute */ User user) { // 생략
		log.debug("Handler2 Invoked");
		
		// 클라이언트가 보낸 데이터로 객체를 생성할 수 있음. => Command 객체
		// Command 객체는 대부분 VO를 가지고 생성함
		// 순서
		// // 1. 만약 클래스 레벨에 @SessionAttribute가 지정되어 있으면 (지정하는 방법은 내일 자세히 배움)
		// //    우선 session에서 user(User)를 찾는다.
		// //    @SessionAttribute가 지정되어있지 않으면 넘어간다.
		// // 2. User의 public 생성자를 찾아서 호출하여 객체를 생성
		// //    생성자가 만약 여러개면 인자가 가장 적은 생성자를 찾는다. (따라서 일반적으로 기본 생성자 선택됨)
		// //    찾은 생성자를 통해 객체 생성
		// // 3. setter를 이용하여 클라이언트가 보내준 데이터를 VO에 저장한다
		// // 4. Model 객체에 해당 VO를 저장한다. Model의 객체가 주입되지 않았더라도 상관 없다.
		// //    이때 기본 키는 VO의 클래스에서 앞 글자를 소문자로 바꾼 문자열.
		// //    키를 바꾸고 싶다면 어노테이션의 인자로 원하는 키 문자열을 전달하면 된다.
		// //    @ModelAttribute("custom-key") Type vo
		
		return "sample05/modelResult";
	}
}
