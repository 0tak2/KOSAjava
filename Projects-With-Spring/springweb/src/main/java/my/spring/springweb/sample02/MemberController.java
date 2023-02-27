package my.spring.springweb.sample02;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.spring.springweb.sample01.TestController04;
import my.spring.springweb.sample02.vo.Member;

@Controller
public class MemberController {
	
//	private static final Logger logger = 
//			LoggerFactory.getLogger(TestController04.class);

	@PostMapping(value = "/member1")
	public ModelAndView myMethod1(
			@RequestParam(value = "name", defaultValue = "없음") String name,
			@RequestParam(value = "phone", defaultValue = "없음") String phone,
			String id, // name과 패러미터 이름이 같으면 어노테이션 생략 가능
			@RequestParam(value = "password", defaultValue = "없음") String password) {
		
		ModelAndView mav = new ModelAndView(); // 보통 뷰와 함께 데이터를 같이 전달할 때 사용
		
		mav.setViewName("sample02/memberView"); // 뷰 이름(문자열)만 넘김. DispatcherServlet는 문자열을 뷰 리졸버에 넘겨서 뷰 객체를 받음
		mav.addObject("name", name); // Model 객체가 아닌 Request 객체에 들어감.
		mav.addObject("phone", phone);
		mav.addObject("id", id);
		mav.addObject("password", password);
		
		return mav;
	}
	
	@PostMapping(value = "/member2")
	public ModelAndView myMethod2(Member vo) {
		// 자동으로 Member의 객체인 vo가 주입된다.
		// Member의 필드명을 읽어와서, 해당 필드와 같은 이름을 request form에서 찾고
		// 그 값을 객체에 넣어 주입해준다.
//		logger.debug(vo.toString()); // vo 값 확인. @Data에 의해 오버라이딩됨
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("memberVO", vo);
		
		mav.setViewName("sample02/memberView");
		
		return mav;
	}
	
	@PostMapping(value = "/member3")
	public String myMethod3(@ModelAttribute Member vo) { // 사실 자동으로 Model 객체에 들어간다. Key는 model (클래스 명에 앞글자 소문자)
		// 자동으로 Member의 객체인 vo가 주입된다.
		// Member의 필드명을 읽어와서, 해당 필드와 같은 이름을 request form에서 찾고
		// 그 값을 객체에 넣어 주입해준다.
//		logger.debug(vo.toString()); // vo 값 확인. @Data에 의해 오버라이딩됨
		
		return "sample02/memberView";
	}
	
	@PostMapping(value = "/member4")
	public String myMethod4(Member vo, String address, Model model) { // 자동으로 이름따라 들어간다
//		logger.debug(vo.toString());
		
		model.addAttribute("address", address);
		
		return "sample02/memberView";
	}
}
