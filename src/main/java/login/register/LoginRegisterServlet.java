package login.register;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import login.register.dao.UserDao;
import login.register.model.UserBean;

@WebServlet("/loginRegister")
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;
	
	@Resource(name="jdbc/test")
	private DataSource dataSource;
       
	@Override
	public void init() throws ServletException {

		super.init();
		
		// create our user dao ... and pass in the conn pool / datasource
		try {
			
			userDao = new UserDao(dataSource);
		}
		catch (Exception exc) {
			
			throw new ServletException(exc);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String submitType = request.getParameter("submit");

	}

}
