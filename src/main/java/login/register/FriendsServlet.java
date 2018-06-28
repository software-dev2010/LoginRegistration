package login.register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

        HttpSession session = request.getSession(false);  
        
        if (session != null) {  
        	
    	    String profileURL = response.encodeURL("ProfileServlet");
    	    session.setAttribute("profileAtr", profileURL);
        	
            request.getRequestDispatcher("friends.jsp").forward(request, response); 
        }
        else {  
        	
            request.setAttribute("message", "Please login first");
            request.getRequestDispatcher("login.jsp").forward(request, response);  
        } 
	}

}
