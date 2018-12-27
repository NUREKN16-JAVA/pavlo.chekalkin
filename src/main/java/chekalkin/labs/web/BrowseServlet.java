package chekalkin.labs.web;

import chekalkin.labs.User;
import chekalkin.labs.db.DaoFactory;
import chekalkin.labs.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class BrowseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (Objects.nonNull(req.getParameter("addButton"))) {
			add(req, resp);
		} else if (Objects.nonNull(req.getParameter("editButton"))) {
			edit(req, resp);
		} else if (Objects.nonNull(req.getParameter("deleteButton"))) {
			delete(req, resp);
		} else if (Objects.nonNull(req.getParameter("detailsButton"))) {
			details(req, resp);
		} else {
			browse(req, resp);
		}
	}

	private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String idString = req.getParameter("id");
	        if (Objects.isNull(idString) || idString.trim().isEmpty()) {
	        	req.setAttribute("error", "You must select a user");
	            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
	            return;
	        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(Long.parseLong(idString));
            req.getSession(true).setAttribute("user", user);
        } catch (Exception e) {
        	req.setAttribute("error", "Error:" + e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/details").forward(req, resp);

	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String idString = req.getParameter("id");
	        if (Objects.isNull(idString) || idString.trim().isEmpty()) {
	        	req.setAttribute("error", "You must select a user");
	            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
	            return;
	        }
	        try {
	            User user = DaoFactory.getInstance().getUserDao().find(Long.parseLong(idString));
	            DaoFactory.getInstance().getUserDao().delete(user);
	        } catch (DatabaseException e) {
	        	req.setAttribute("error", "Error:" + e.toString());
	            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
	            return;
	        }
	        resp.sendRedirect("/browse");

	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id");
        if (Objects.isNull(idString) || idString.trim().isEmpty()) {
        	req.setAttribute("error", "You must select a user");
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(Long.parseLong(idString));
            req.getSession(true).setAttribute("user", user);
        } catch (Exception e) {
        	req.setAttribute("error", "Error:" + e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/edit").forward(req, resp);
		
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add").forward(req, resp);

	}

	private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Collection<User> users = DaoFactory.getInstance().getUserDao().findAll();
			req.getSession(true).setAttribute("users", users);
			req.getRequestDispatcher("/browse.jsp").forward(req, resp);
		} catch (DatabaseException e) {
			throw new ServletException(e);
		}
	}
}
