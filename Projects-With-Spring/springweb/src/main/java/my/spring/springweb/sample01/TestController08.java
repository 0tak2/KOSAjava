package my.spring.springweb.sample01;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.spring.springweb.sample01.vo.User;

@Controller
@RequestMapping(value="/testController08")
public class TestController08 {

//	private static final Logger logger =
//			LoggerFactory.getLogger(TestController08.class);
	
	@PostMapping("useEL")
	public String myMethod(Model model) {
		
		model.addAttribute("myName", "홍길동");
		model.addAttribute("myAge", 20);
		
		List<String> list = new ArrayList<String>();
		list.add("강감찬");
		list.add("신사임당");
		
		model.addAttribute("myList", list);
		
		User user = new User("이순신", 40);
		model.addAttribute("myUser", user);
		
		return "sample01/testController08";
	}
}
