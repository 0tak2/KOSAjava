package my.spring.springweb.sample09;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.spring.springweb.sample09.vo.User;

@RestController
@RequestMapping(value = "/body")
public class ResponseBodyController {

	@RequestMapping(value = "text/{id}",
			produces = "text/plain; charset=UTF-8") // PathVariable로 받을 수 있음
	public String method01(@PathVariable String id) {
		
		return "<h1>이것은 소리 없는 아우성!! " + id + " </h1>";
		// 리턴되면 MessageConverter에 의해 변환
		// 이때 @RequestMapping을 읽어 produces의 형태에 맞는 구현체를 이용
		// 변환 결과를 DispathcerServlet에서 받아 http response 전송
	}
	
	@RequestMapping(value = "textObject/{id}",
			produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> method02(@PathVariable String id) { // data의 타입을 제네릭으로 지정
		String msg = "<h1>이것은 소리 없는 아우성!! " + id + " </h1>"; // data
		HttpHeaders headers = new HttpHeaders(); // headers
		headers.setContentType(new MediaType("text", "plain",
				Charset.forName("UTF-8"))); // text/plain
		
		return new ResponseEntity<String>(msg, headers, HttpStatus.OK); // 200
	}
	
	@RequestMapping(value = "/json/{name}",
			produces = "application/json; charset=UTF-8")
	public User method03(@PathVariable String name) {
		User user = new User();
		user.setName(name);
		user.setAddr("서울");
		
		return user;
	}
	
}
