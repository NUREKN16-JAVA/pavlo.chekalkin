package chekalkin.labs.web;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.util.Date;
import chekalkin.labs.User;

public class AddServletTest extends MockServletTestCase {
	@Override
	public void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	public void testAdd() {
		Date date = new Date();
		User user = new User(new Long(1000), "John", "Doe", date);
		User newUser = new User("John", "Doe", date);
		getMockUserDao().expectAndReturn("create", newUser, user);
		
		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}

	public void testAddEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	public void testAddEmptyLastName() {
		Date date = new Date();
		addRequestParameter("firstName", "John");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	public void testAddEmptyDate() {
		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	public void testAddIncorrectDate() {
		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", "fdsagasgas");
		addRequestParameter("okButton", "Ok");

		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
}
