package my.spring.springweb.sample06.dao;

import java.util.Random;

import org.springframework.stereotype.Repository;

@Repository("lottoDao") // 빈으로 등록; 빈 ID 지정 가능
public class LottoDao {

	public LottoDao() {
	}
	
	public int selectLottoNumber() {
		Random rand = new Random();
		return rand.nextInt(6) + 1; // 1, 2, 3, 4, 5, 6
	}
}
