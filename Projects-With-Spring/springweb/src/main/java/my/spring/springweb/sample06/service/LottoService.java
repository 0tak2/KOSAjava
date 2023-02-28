package my.spring.springweb.sample06.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import my.spring.springweb.sample06.dao.LottoDao;
import my.spring.springweb.sample06.vo.Lotto;

@Service
public class LottoService {

	Logger log = LogManager.getLogger("case3");
	
	// @Autowired // 타입으로 찾음
	// @Resource(name = "lottoDao") // 이름으로 찾음
	@Autowired
	@Qualifier(value = "lottoDao") // 원래 타입으로 찾지만, 이름으로 찾도록 변경
	private LottoDao dao;
	
	public LottoService() {
	}
	
	public boolean checkLottoNumber(Lotto lotto) {
		// 일반적인 로직처리 진행은 여기서
		// DB처리는 DAO가 필요
		
		boolean result = false;
		int randomNumber = dao.selectLottoNumber(); // 랜덤번호
		
		log.debug("선택된 로또 번호: " + lotto.getLottoNum());
		log.debug("생성된 당첨번호: " + randomNumber);
		
		if (randomNumber == lotto.getLottoNum()) {
			log.debug("당첨");
			result = true;
		}
		return result;
	}
}
