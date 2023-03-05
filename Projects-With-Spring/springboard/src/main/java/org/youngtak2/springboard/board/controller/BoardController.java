package org.youngtak2.springboard.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.youngtak2.springboard.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardService service;
	
	@GetMapping(value = "/")
	public String boardMain() {
		return "board/boardMain";
	}
}
