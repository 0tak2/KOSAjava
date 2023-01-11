package example.controller;

import example.service.ButtonService;

public class Button1Controller {

	public String exec() {
		// 여기서 서비스에 대해 로직 처리를 맡김
		
		ButtonService service = new ButtonService();
		String result = service.clickButton();
		return result;
	}

}
