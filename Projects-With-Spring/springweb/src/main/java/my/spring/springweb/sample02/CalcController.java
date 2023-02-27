package my.spring.springweb.sample02;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.spring.springweb.sample02.vo.User;

@Controller
@RequestMapping("/calc.do")
public class CalcController {

//	private static final Logger logger = 
//			LoggerFactory.getLogger(CalcController.class);
	
	@PostMapping
	public ModelAndView process(int firstNum, int secondNum, // @RequestParam 생략
			String operator) {
		
		ModelAndView mav = new ModelAndView();
		String viewName = "";
		
		if (operator.equals("div") && secondNum == 0) { //  자바가 처리할 수 없는 분모가 0인 나눗셈
			viewName = "sample02/errorResult";
			mav.addObject("msg", "분모는 0이 될 수 없습니다.");
			// Model의 객체를 주입받은 상황이 아닌, ModelAndView 객체를 생성하여 넣어주는 상황
			// addAttribute가 아닌 addObject 사용
			// 이때에는 Request의 객체에 데이터가 붙게 됨.
		} else {
			int result = 0;
			if (operator.equals("plus")) {
				result = firstNum + secondNum;
			} else if (operator.equals("minus")) {
				result = firstNum - secondNum;
			} else if (operator.equals("multi")) {
				result = firstNum * secondNum;
			} else {
				result = firstNum / secondNum;
			}
			viewName = "sample02/calcResult";
			mav.addObject("msg", result);
		}
		
		mav.setViewName(viewName); // 뷰 객체가 아닌 뷰의 이름을 넣어줌. View Resolver를 통해 파일을 찾아 뷰 객체 생성
		return mav;
	}
	
}
