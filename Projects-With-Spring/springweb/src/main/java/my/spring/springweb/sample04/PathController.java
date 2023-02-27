package my.spring.springweb.sample04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {

	@RequestMapping(value = "/character/detail/{name}/{number}")
	public String handler1(@PathVariable("name") String name,
			@PathVariable("number") int num,
			Model model) {
		
		if(name.equals("kakao")) {
			if(num == 1) {
				model.addAttribute("imgname", "ryan");
			} else if (num == 2) {
				model.addAttribute("imgname", "apeach");
			} else if (num == 3) {
				model.addAttribute("imgname", "frodoneo");
			}
		}
		
		if(name.equals("line")) {
			if(num == 1) {
				model.addAttribute("imgname", "brown");
			} else if (num == 2) {
				model.addAttribute("imgname", "james");
			} else if (num == 3) {
				model.addAttribute("imgname", "cony");
			}
		}
		
		return "sample04/detailView";
	}
}
