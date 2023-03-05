package org.youngtak2.springboard.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private Logger log = LogManager.getLogger("base");
	
	@GetMapping(value = "/")
	public String home(Locale locale, Model model) {
		return "redirect:/board/";
	}
	
}
