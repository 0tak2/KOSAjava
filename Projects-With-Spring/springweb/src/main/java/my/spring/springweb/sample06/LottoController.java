package my.spring.springweb.sample06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import my.spring.springweb.sample06.service.LottoService;
import my.spring.springweb.sample06.vo.Lotto;

@Controller
public class LottoController {
	
	@Autowired
	private LottoService service;
	
	@GetMapping(value = "/lotto")
	public String lottoProcess(/* @ModelAttribute */ Lotto lotto) {
		boolean result = service.checkLottoNumber(lotto);	
		if (result) {
			lotto.setResult("축하합니다. 당첨되셨습니다.");
		} else {
			lotto.setResult("아쉽지만 낙첨되셨습니다.");
		}
		return "sample06/lottoView"; // vo는 이미 @ModelAttribute에 의해 모델에 들어가있음
	}
}
