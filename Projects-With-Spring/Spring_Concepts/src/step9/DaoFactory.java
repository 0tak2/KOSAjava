package step9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import step9.dao.ConnectionMaker;
import step9.dao.SimpleMakeConnection;
import step9.dao.UserDao;

public class DaoFactory {

	public UserDao userDao() {
//		UserDao dao = new UserDao(connectionMaker());
		UserDao dao = new UserDao();
		dao.setConnectionMaker(connectionMaker());
		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new SimpleMakeConnection();
	}
}
