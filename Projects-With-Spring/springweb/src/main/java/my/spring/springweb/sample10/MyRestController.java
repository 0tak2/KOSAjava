package my.spring.springweb.sample10;

import java.nio.charset.Charset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.spring.springweb.sample10.vo.User;

@RestController
@RequestMapping(value = "/rest/user")
public class MyRestController {
	
	Logger log = LogManager.getLogger("case3");
	
	@GetMapping
	public ResponseEntity<User> method01(@ModelAttribute User user) {
		log.debug("GET /rest/user");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<User> method02(@ModelAttribute User user) {
		log.debug("DELETE /rest/user");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}
}
