package my.spring.springweb.sample07;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping(value = "/sessionAttributesTest02")
public class SessionAttributeController02 {

	Logger log = LogManager.getLogger("case3");
	
	@ModelAttribute("data1")
	public String createString1() {
		log.debug("[Invoked] createString1");
		return "createString1";
	}
	
	@ModelAttribute("data2")
	public String createString2(HttpServletRequest request) {
		// 세션 직접 이용
		//	true => 접속 클라리언트에 대한 세션이 없으면 만들고 레퍼런스를 가져옴.
		//			있으면 레퍼런스만 가져옴.
		HttpSession session = request.getSession(true);
		Student student = new Student(30, "신사임당", "국어국문");
		session.setAttribute("currentStudent", student); 
		
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
		
		return "sample07/sessionResult02";
	}
}
