package chekalkin.labs.db;

import com.mockobjects.dynamic.Mock;

import chekalkin.labs.db.DaoFactory;
import chekalkin.labs.db.UserDao;


public class MockDaoFactory extends DaoFactory {
	
	private Mock mockUserDao;
	
	public MockDaoFactory() {
		mockUserDao = new Mock(UserDao.class);
	}

	public Mock getMockUserDao() {
		return mockUserDao;
	}
	
	@Override
	public UserDao getUserDao() {
		
		return (UserDao) mockUserDao.proxy();
	}

}
