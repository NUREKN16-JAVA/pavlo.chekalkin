package chekalkin.labs.db;

import junit.framework.TestCase;
import chekalkin.labs.db.DaoFactory;
import chekalkin.labs.db.UserDao;

public class DaoFactoryTest extends TestCase {

	public void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null", daoFactory);
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull("UserDao intance is null", userDao);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

}
