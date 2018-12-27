package chekalkin.labs.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Test;

import chekalkin.labs.User;
import chekalkin.labs.db.ConnectionFactory;
import chekalkin.labs.db.ConnectionFactoryImpl;
import chekalkin.labs.db.DatabaseException;
import chekalkin.labs.db.HsqldbUserDao;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	private static final Long ID = 0L;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/labs", "sa", "");
		dao = new HsqldbUserDao(connectionFactory);
	}
	
	@Test
	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Ivan");
			user.setLastName("Ivanov");
			user.setBirthday(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString()); 
		}
	}
	
	@Test
	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
    public void testFind () throws DatabaseException {
		try {
			User user = new User(1L, "Ivan","Ivanov",new Date());
			User testUser = new User(ID, "Ivan", "Ivanov", new Date());
			assertNotNull(testUser);
			assertEquals( user.getFirstName(),testUser.getFirstName());
			assertEquals(user.getLastName(),testUser.getLastName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	 @Test
	    public void testUpdate() throws DatabaseException {
	        try {
				User user = new User(1L, "Ivan","Ivanov",new Date());
				user.setId(0L);
				dao.update(user);
				User testUser = dao.find(user.getId());
				assertNotNull(testUser);
				assertEquals(user.getLastName(), testUser.getLastName());
			} catch (DatabaseException e) {
				e.printStackTrace();
			}
	    }
	 
	 @Test
	    public void testDelete() throws DatabaseException {
	        try {
				User testUser = new User(ID, "Ivan", "Ivanov", new Date());
				dao.delete(testUser);
				assertNull(dao.find(ID));
			} catch (DatabaseException e) {
				e.printStackTrace();
			}
	    }
	

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/labs", "sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
