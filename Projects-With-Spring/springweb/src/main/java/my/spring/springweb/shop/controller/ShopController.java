package my.spring.springweb.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import my.spring.springweb.shop.vo.Basket;

@Controller
@RequestMapping(value = "/shop")
@SessionAttributes(value = {"myBasket"})
public class ShopController {


	@ModelAttribute(value = "myBasket")
	public Basket getBasket() {
		return new Basket();
	}
	
	@GetMapping(value = "/select")
	public String viewBasket() {
		
		return "shop/viewBasket";
	}
	
	@PostMapping(value = "/select")
	public String selectItem(@ModelAttribute("myBasket") Basket basket) {
		
		return "shop/viewBasket";
	}
	
	@GetMapping(value = "/purchase")
	public String clearBasket(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/shop/select";
	}
}
