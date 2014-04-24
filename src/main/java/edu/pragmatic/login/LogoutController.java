package edu.pragmatic.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static edu.pragmatic.login.LoginController.CURRENT_USER;

@WebServlet("/logout")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object userName = session.getAttribute(CURRENT_USER);
		if (userName == null){
			response.getOutputStream().println("No user is yet logged in. Please log in first");
			return;
		}
		
		session.invalidate(); // actually log out
		response.getOutputStream().print("You've successfully logged out");
	}
	
}
