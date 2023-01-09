package example.main;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import example.dao.BookDAO;
import example.mybatis.MyBatisConnectionFactory;

public class MainPrev {

	public static void main(String[] args) {
		// 책을 조회하고 삭제처리하는 데이터베이스 처리를
		// MyBatis를 이용하여 해본다
		
		// 예제이므로 DAO만을 만들어서 처리 (Controller, Service 적용 X)
		
		SqlSessionFactory factory = 
				MyBatisConnectionFactory.getSqlSessionFactory();
		
		BookDAO dao = new BookDAO(factory); // 기존 프로젝트에서는 DAO에 커넥션을 넘겨 줬지만,
										// 여기서는 SqlSessionFactory를 넘겨줄 것임
		
		// 1. 책의 ISBN을 이용해 책 1권의 데이터를 가져와 출력
		//    DAO에는 비즈니스 로직을 배제해야하며,
		//    메서드명으로 selectOne, selectAll 정도가 적절하지만,
		//    본 예제에서는 Controller와 Service를 적용하지 않고 있으므로
		//    구체적으로 명명하였음
		
		/*    아래와 같은 HashMap으로 결과를 받아오려고 함
			 +---------+----------+
			| Key     | Value    |
			+---------+----------+
			| bisbn   | 123-45-6 |
			+---------+----------+
			| btitle  | 홍길동전 |
			+---------+----------+
			| bauthor | 허균     |
			+---------+----------+
			| bprice  | 13000    |
			+---------+----------+
		 */
		
		System.out.println("1. 책의 ISBN을 이용해 책 1권의 데이터를 가져와 출력");
		HashMap<String, Object> map = dao.selectByISBNHashMap("89-7914-206-4");
		for (String key : map.keySet()) {
			System.out.println(key + ", " + map.get(key));
		}
		System.out.println();
		
		// 2. 조건 없이 모든 책의 데이터를 가져와 출력
		System.out.println("2. 조건 없이 모든 책의 데이터를 가져와 출력");
		List<HashMap<String, Object>> list = dao.selectAllHashMap();
		
		for (HashMap<String, Object> rowAsMap : list) {
			for (String key : rowAsMap.keySet()) {
				System.out.println(key + ", " + rowAsMap.get(key));
			}
			System.out.println();
		}
		
	}
}
