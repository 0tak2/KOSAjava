package my.spring.springweb.sample07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import my.spring.springweb.sample07.vo.Student;

@Controller
@SessionAttributes({"data1"})
@RequestMapping(value = "sessionAttributesTest01")
public class SessionAttributeController01 {

	Logger log = LogManager.getLogger("case3");
	
	// 핸들러 호출 직전 아래 메서드가 호출됨
	// 아래 메서드의 리턴값이 모델에 저장됨. key는 "data1"
	@ModelAttribute("data1")
	public String createString1() {
		log.debug("[Invoked] createString1");
		return "createString1";
	}
	
	// 역시 핸들러 호출 직전 아래 메서드가 호출됨
	// createString1, createString2 간 호출 순서는 알 수 없음.
	@ModelAttribute("data2")
	public String createString2() {
		log.debug("[Invoked] createString2");
		return "createString2";
	}	
	
	@PostMapping
	public String handler(@ModelAttribute("data1") String str1,
			@ModelAttribute("data2") String str2, // 위의 리턴값을 인자로 주입받음
			@RequestParam(value = "msg") String strFromReq, // 리퀘스트 오는 데이터 중 VO에 없는 것
			@ModelAttribute("newStudent") Student studentVO) { // 레퀘스트 데이터를 VO로 받음 (키값 지정)
		
		log.debug("[Invoked] handler(): " + str1 + ", " + str2);
		log.debug("\t msg: " + strFromReq);
		log.debug("\t student: " + studentVO);
		
		return "sample07/sessionResult01";
	}
}
