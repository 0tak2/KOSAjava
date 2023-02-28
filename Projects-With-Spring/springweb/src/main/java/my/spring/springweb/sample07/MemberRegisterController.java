package my.spring.springweb.sample07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import sample07.vo.Member;

@Controller
@SessionAttributes(value = {"memberInfo"})
public class MemberRegisterController {

	Logger log = LogManager.getLogger("case3");
	
	@ModelAttribute("memberInfo")
	public Member createMember() {
		log.debug("[createMember] invoked");
		Member member = new Member();
		return member;
	}
	
	@PostMapping(value = "memberRegisterStep1")
	public String handler1() {
		log.debug("[handler1] invoked / 회원가입 1단계 페이지 전송");
		return "sample07/step1";
	}
	
	@PostMapping(value = "memberRegisterStep2")
	public String handler2(@ModelAttribute("memberInfo") Member member) {
		log.debug("[handler2] invoked / 회원가입 2단계 페이지 전송");
		log.debug(member);
		
		return "sample07/step2";
	}
	
	@PostMapping(value = "memberRegisterStep3")
	public String handler3(@ModelAttribute("memberInfo") Member member, // 1. 세션에 있는 객체 가져옴 2. 리퀘스트로부터 세터 호출
			SessionStatus sessionStatus) { // 위에서 @SessionAttributes로 지정한 키 값을 만료시킬 수 있음
		log.debug("[handler2] invoked / 저장");
		log.debug(member);
		
		// TODO Service와 Repository를 이용해 저장 처리
		
		// TODO 저장 후 세션 날리기
		 // 세션에서 위의 @SessionAttributes로 지정한 키를 제거.
		sessionStatus.setComplete(); // 세션 자체는 살아있음.
		
		return "redirect:/memberRegisterComplete"; // sendRedirect와 같은 역할. 'redirect:/'
	}
	
	@GetMapping(value = "memberRegisterComplete")
	public String handler4() {
		return "sample07/step3";
	}
}
