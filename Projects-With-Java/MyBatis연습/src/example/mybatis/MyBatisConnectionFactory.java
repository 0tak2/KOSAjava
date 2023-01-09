package example.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		// resources 폴더도 소스 폴더로 간주되기 때문에 .으로 접근 가능
		String resource = "./SqlMapConfig.xml";
		
		// SqlMapConfig.xml를 읽기 위한 스트림 준비
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			
			if (sqlSessionFactory == null) {
				// sqlSessionFactory 인스턴스가 아직
				// 생성되지 않은 경우에만 인스턴스 생성
				// == 프로그램 실행 중 한 번만 인스턴스 생성 (싱글톤)
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
