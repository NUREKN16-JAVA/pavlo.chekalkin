package chekalkin.labs.web;

import static org.junit.Assert.assertNull;

import chekalkin.labs.User;
import chekalkin.labs.web.DetailsServlet;

public class DetailsServletTest extends MockServletTestCase {
	@Override
	public void setUp() throws Exception {
		super.setUp();
		createServlet(DetailsServlet.class);
	}

	public void testBack() {
		addRequestParameter("backButton", "Back");
		User user = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNull("User was not deleted from session", user);
	}
}
