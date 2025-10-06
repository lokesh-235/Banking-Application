package BankApplication;

import jakarta.servlet.RequestDispatcher;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import models.Admin;

/**
 * Servlet implementation class adminLogin
 */
@WebServlet("/adminLogin")
public class adminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd=request.getRequestDispatcher("adminLogin.jsp");
		rd.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email == null || email.isBlank() || password == null || password.isBlank()) {
		    request.setAttribute("msg", "Email and Password are required!");
		    doGet(request, response);
		    return;
		}

		
		
		Admin admin = Admin.getAdminByEmail(email);
		
		
				if(password.equals(admin.getPassword())) {
					
					
					System.out.println(admin);
					HttpSession session = request.getSession();
					session.setAttribute("admin", admin);
					
					
					request.setAttribute("msg", "login successful");
					
					response.sendRedirect(request.getContextPath()+"/adminDashboard");
					
				}
				else {
					request.setAttribute("msg", "Invalid email/password");
					doGet(request,response);
				}
			}
			
}

	
