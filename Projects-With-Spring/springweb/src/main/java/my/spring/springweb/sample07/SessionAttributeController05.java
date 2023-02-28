package my.spring.springweb.sample07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import my.spring.springweb.sample07.vo.Student;

@Controller
@SessionAttributes({"data1", "shin", "newStudent"})
@RequestMapping(value = "/sessionAttributesTest05")
public class SessionAttributeController05 {

	Logger log = LogManager.getLogger("case3");
	
	@ModelAttribute("data1")
	public String createString1() {
		log.debug("[Invoked] createString1");
		return "createString1";
	}
	
	@ModelAttribute("data2")
	public String createString2(Model model) {
		Student student = new Student(30, "신사임당", "국어국문");
		
		model.addAttribute("shin", student);
		
		log.debug("[Invoked] createString2");
		return "createString1";
	}
	
	@PostMapping
	public String handler(@ModelAttribute("data1") String str1,
			@ModelAttribute("data2") String str2, // 위의 리턴값을 인자로 주입받음
			@RequestParam(value = "msg") String strFromReq, // 리퀘스트 오는 데이터 중 VO에 없는 것
			@ModelAttribute("newStudent") Student studentVO) { // 레퀘스트 데이터를 VO로 받음 (키값 지정)
		
		log.debug("[Invoked] handler(): " + str1 + ", " + str2);
		log.debug("\t msg: " + strFromReq);
		log.debug("\t student: " + studentVO);
		
		return "sample07/sessionResult05";
	}
}
