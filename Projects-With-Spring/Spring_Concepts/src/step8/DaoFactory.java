package step8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import step8.dao.ConnectionMaker;
import step8.dao.SimpleMakeConnection;
import step8.dao.UserDao;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMaker());
		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new SimpleMakeConnection();
	}
}
