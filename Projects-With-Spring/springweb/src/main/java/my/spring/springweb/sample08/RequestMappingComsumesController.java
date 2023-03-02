package my.spring.springweb.sample08;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestMappingComsumesController {

	Logger log = LogManager.getLogger("case3");
	
	@RequestMapping(value = "/testController1", method = RequestMethod.GET,
			consumes = {"application/json", "application/xml"})
	public String myMethod() {
		log.debug("GET 방식 요청 받음");
		
		return null;
	}
	
	@RequestMapping(value = "/testController2", method = RequestMethod.POST,
			consumes = {"application/json", "application/x-www-form-urlencoded"})
	public String myMethod2() {
		log.debug("POST 방식 요청 받음");
		
		return null;
	}
}
