package my.spring.springweb.sample08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import my.spring.springweb.common.ApplicationContextProvider;

@Controller
public class RequestMappingProducesController {

	Logger log = LogManager.getLogger("case3");
	
	// @Autowired
	// private Gson gson;
	
	@RequestMapping(value = "/testProduces1")
	public String method01(Model model) {
		model.addAttribute("msg", "소리 없는 아우성!");
		return "sample08/requestMappingProducesView";
	}
	
	@RequestMapping(value = "/testProduces2", produces = "text/plain; charset=UTF-8")
	public String method02(Model model) {
		model.addAttribute("msg", "소리 없는 아우성!");
		return "sample08/requestMappingProducesView";
	}
	
	@RequestMapping(value = "/testProduces3", produces = "text/html; charset=UTF-8")
	public void method03(HttpServletResponse response) {
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<html><head></head>");
			out.println("<body><h1>소리 없는 아우성!!</h1></body></html>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/testProduces4",
					produces = "application/json; charset=UTF-8") // stream으로 직접 출력되므로 어짜피 의미 없다.ㄴ
	public void method04(HttpServletResponse response) {
		
		try {
			response.setContentType("application/json; charset=UTF-8"); // produces 무시
			PrintWriter out = response.getWriter();
			
			// 데이터 작성
			Map<String, String> map = new HashMap<String, String>();
			map.put("userNmae", "홍길동");
			map.put("userAge", "20");
			map.put("userAddr", "서울");
			
			// 객체 to JSON 변환
			ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
			Gson gson = ctx.getBean("getGson", Gson.class);
			String str = gson.toJson(map);
			
			// Stream을 통해 클라이언트에게 전송
			out.println(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
