package step7.dao;

public class DaoFactory {

	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMaker());
		return dao;
	}
	
	public ConnectionMaker connectionMaker() {
		return new SimpleMakeConnection();
	}
}
