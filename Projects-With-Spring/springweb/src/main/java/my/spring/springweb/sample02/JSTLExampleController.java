package my.spring.springweb.sample02;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JSTLExampleController {

	@RequestMapping(value="/jstlExample", method = RequestMethod.GET)
	public String jstlExampleHandler(Model model) {
		List<String> list = new ArrayList<String>();
		list.add("홍길동");
		list.add("신사임당");
		list.add("강감찬");
		
		model.addAttribute("myNum", 100);
		model.addAttribute("myList", list);
		
		return "sample02/jstlExample";
	}
	
}
