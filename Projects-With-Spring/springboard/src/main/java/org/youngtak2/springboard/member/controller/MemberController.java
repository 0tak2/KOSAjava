package org.youngtak2.springboard.member.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.youngtak2.springboard.member.service.MemberService;
import org.youngtak2.springboard.member.vo.Member;

@Controller
@RequestMapping(value = "/member")
@SessionAttributes(value = {"currentMember"})
public class MemberController {
	
	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	MemberService service;

	@ModelAttribute("currentMember")
	public Member setMemberToSession() {
		return new Member();
	}
	
	@GetMapping(value = "/login")
	public String getLoginHandler(@SessionAttribute(required = false) Member currentMember, SessionStatus sessionStatus) {
		if (currentMember != null) {
			log.debug("이미 로그인되어 있음 - 로그인정보 날림");
			sessionStatus.setComplete();
		}
		
		return "member/login";
	}
	
	@PostMapping(value = "/login")
	public String postLoginHandler(@ModelAttribute Member member,
			Model model,
			SessionStatus sessionStatus) {
		log.debug("로그인 정보 전송받음: " + member);
		
		Member memberInfo = service.login(member);
		
		if(memberInfo == null) {
			sessionStatus.setComplete();
		} else {
			model.addAttribute("currentMember", memberInfo);
		}
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/register")
	public String getRegisterHandler() {
		return "member/register";
	}
	
	@PostMapping(value = "/register")
	public String postRegisterHandler(@ModelAttribute Member newMember, Model model) {
		boolean result = service.registerNewAccount(newMember);
		
		model.addAttribute("result", result);
		
		return "member/registerResult";
	}
}
