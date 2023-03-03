package my.spring.springweb.sample11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.spring.springweb.sample11.dao.BookDao;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	
	@Autowired
	private BookDao dao;
	
	@GetMapping(value = "/count")
	public String method01(Model model) {
		model.addAttribute("result", dao.getBookCount());
		return "sample11/bookCount";
	}
	
	@GetMapping(value = "/all")
	public String method02(Model model) {
		model.addAttribute("result", dao.gettAllBooks());
		return "sample11/bookAll";
	}
	
	@PostMapping(value = "/search")
	public String method03(@RequestParam String keyword, Model model) {
		model.addAttribute("query", keyword);
		model.addAttribute("result", dao.getSearchBooks(keyword));
		return "sample11/bookFound";
	}
}
