package chekalkin.labs.web;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import chekalkin.labs.User;
import chekalkin.labs.db.DatabaseException;

public class BrowseServletTest extends MockServletTestCase{
	
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        List<User> list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);
        doGet();
        @SuppressWarnings("unchecked")
		Collection<User> collection = (Collection<User>) getWebMockObjectFactory()
                .getMockSession()
                .getAttribute("users");
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
    }
    
    public void testEdit() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", new Long(1000), user);
        addRequestParameter("editButton", "Edit");
        addRequestParameter("id", "1000");
        doPost();
        User actualUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in session", actualUser);
        assertEquals(user, actualUser);
    }
    
    public void testDetails() {
    	User user = new User(new Long(1000), "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", new Long(1000), user);
        addRequestParameter("detailsButton", "Details");
        addRequestParameter("id", "1000");
        doPost();
        User actualUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in session", actualUser);
        assertEquals(user, actualUser);
    }

    public void testDelete() {
    	User user = new User(new Long(1000), "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", new Long(1000), user);
        getMockUserDao().expect("delete", user);
        addRequestParameter("deleteButton", "Delete");
        addRequestParameter("id", "1000");
        doPost();
    }

    public void testDeleteWithIncorrectId() {
        String errorMessage = "Incorrect id";
        getMockUserDao().expectAndThrow("find", new Long(1000), new DatabaseException(errorMessage));
        addRequestParameter("deleteButton", "Delete");
        addRequestParameter("id", "1000");
        doPost();
        String mockErrorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message", mockErrorMessage);
        assertTrue(mockErrorMessage.contains(mockErrorMessage));
    }
    
}
