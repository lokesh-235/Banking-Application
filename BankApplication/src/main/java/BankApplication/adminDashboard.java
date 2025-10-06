package BankApplication;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import models.Admin;
/**
 * Servlet implementation class adminDashboard
 */
@WebServlet("/adminDashboard")
public class adminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("admin")!=null)  {
			Admin admin = (Admin) session.getAttribute("admin");
		    System.out.println("empName : "+admin.getName());
		    request.setAttribute("empName", admin.getName());
		    request.setAttribute("empEmail", admin.getEmail());

		    RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
		    rd.forward(request, response);
		} else {
		    response.sendRedirect(request.getContextPath() + "/adminLogin");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 TODO Auto-generated method stub
		doGet(request, response);
	}

}
