package chekalkin.labs.web;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (Objects.nonNull((req.getParameter("backButton")))) {
			req.getSession(true).removeAttribute("user");
			redirect(req, resp, "/browse");
		} else {
			redirect(req, resp, "/details.jsp");
		}
	}

	private void redirect(HttpServletRequest req, HttpServletResponse resp, String path)
			throws ServletException, IOException {
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
