package BankApplication;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Admin;

import java.io.IOException;
import models.Account;
/**
 * Servlet implementation class createAccount
 */
@WebServlet("/createAccount")
public class createAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createAccount() {
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
		   
		    request.setAttribute("empName", admin.getName());
		    request.setAttribute("empEmail", admin.getEmail());

		    RequestDispatcher rd = request.getRequestDispatcher("createAccount.jsp");
		    rd.forward(request, response);
		} else {
		    response.sendRedirect(request.getContextPath() + "/adminLogin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name;
		String email;
		String password;
		int initialBalance;
		try {
			name = request.getParameter("name");
			email = request.getParameter("email");
			password = request.getParameter("password");
			initialBalance = Integer.parseInt(request.getParameter("initialBalance"));
			
			if(initialBalance<=0) {
				request.setAttribute("msg", "Please enter valid amount/account number");
				doGet(request,response);
				return;
				
			}
			
			else {
				    Account account = Account.createAccount(name, email, password, initialBalance);
					if(account!=null) {
						request.setAttribute("msg", "Account created successfully");
						System.out.println(account);
						doGet(request,response);
					}
					else {
						request.setAttribute("msg", "account creation failed");
						doGet(request,response);
					}
			}
			
			} catch(NumberFormatException e) {
				request.setAttribute("msg", "Please enter only numbers");
				doGet(request,response);
//				response.sendRedirect(request.getContextPath()+"/creditAmount");
				
			}
	}

}
