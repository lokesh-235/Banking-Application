package BankApplication;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import models.Account;
import models.Admin;
import models.Transactions;

/**
 * Servlet implementation class viewAccount
 */
@WebServlet("/viewAccount")
public class viewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewAccount() {
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

		    RequestDispatcher rd = request.getRequestDispatcher("viewAccount.jsp");
		    rd.forward(request, response);
		} else {
		    response.sendRedirect(request.getContextPath() + "/adminLogin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {

	    HttpSession session = request.getSession(false);

	    if (session != null && session.getAttribute("admin") != null) {
	        Admin admin = (Admin) session.getAttribute("admin");
	        request.setAttribute("empName", admin.getName());
	        request.setAttribute("empEmail", admin.getEmail());

	        int accountNumber = Integer.parseInt(request.getParameter("acno"));
	        Account account = Account.getAccountByAccountNumber(accountNumber);

	        if (account != null) {
	            request.setAttribute("msg", "Account exists");
	            request.setAttribute("account", account); // ✅ rename to 'account' (easier in JSP)
	            
	            List<Transactions> transactions = Transactions.findTransactionsByAccountNumber(accountNumber);
	            request.setAttribute("transactions", transactions);
	        } else {
	            request.setAttribute("msg", "Account does not exist");
	        }

	        RequestDispatcher rd = request.getRequestDispatcher("viewAccount.jsp");
	        rd.forward(request, response);

	    } else {
	        response.sendRedirect(request.getContextPath() + "/adminLogin");
	    }
	}


}
