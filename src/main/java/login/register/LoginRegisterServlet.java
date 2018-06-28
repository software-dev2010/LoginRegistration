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

		try {
			
			String submitType = request.getParameter("submit"); 
			
			if (submitType.equals("Register")) {
				
				registerUser(request, response);
			} else {
				
				loginUser(request, response);
			}
						
		} catch (Exception exc) {
			
			throw new ServletException(exc);
		} 

	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		
		UserBean user = userDao.getUser(username, password);
		
		if (user != null) {
			
			// request.setAttribute("message", user.getName());
			
            HttpSession session = request.getSession();  
            session.setAttribute("nameAtr", user.getName());  
            
    	    // Encodes the specified URL by including the session ID in it,
    	    // or, if encoding is not needed, returns the URL unchanged
    	    String profileURL = response.encodeURL("ProfileServlet");
    	    String friendsURL = response.encodeURL("FriendsServlet");
    	    session.setAttribute("profileAtr", profileURL);
    	    session.setAttribute("friendsAtr", friendsURL);
			
			request.getRequestDispatcher("welcome.jsp").forward(request, response); 
		} else {
			
			request.setAttribute("message", "Incorrect username or password. <br/>Please try again!");
			request.getRequestDispatcher("login.jsp").forward(request, response); 
		}

	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String confirmPassword = request.getParameter("password2");
		String name = request.getParameter("name");  

		UserBean user = new UserBean(); 
		
		user.setUsername(username);  
		user.setPassword(password); 
		user.setName(name);
		
		if (password.equals(confirmPassword)) {
			
			userDao.insertUser(user); 

			request.setAttribute("successMessage", "Registration complete!");
			request.getRequestDispatcher("login.jsp").forward(request, response); 
		} else {
			
			request.setAttribute("errorMessage", "Passwords did not match. <br/> Please enter the same password in both fields.");
			request.setAttribute("userName", username);
			request.setAttribute("Name", name);
			
			request.getRequestDispatcher("register.jsp").forward(request, response); 
		}

	}

}
