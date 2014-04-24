package edu.pragmatic.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public static final String CURRENT_USER = "currentUser";

	public Map<String, String> credentials;
	
	@Override
	public void init() throws ServletException {
		super.init();
		credentials = new HashMap<>();
		credentials.put("Peter", "secretPassword");
		credentials.put("John", "awesomePass");
		credentials.put("Merry", "_The_passw0pd");
		credentials.put("Jane", "odrmLPP7oITdZh04yEm7");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/page/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String name = request.getParameter("name");
		if (!credentials.containsKey(name)) {
			resp.getOutputStream().print("Wrong Credentials");
			return ;
		}
		String password = request.getParameter("password");
		if (!credentials.get(name).equals(password)){
			resp.getOutputStream().print("Wrong Credentials");
			return ;
		}
		
		request.getSession().setAttribute(CURRENT_USER, name);
		resp.getOutputStream().print("Welcome " + name );
	}

}
