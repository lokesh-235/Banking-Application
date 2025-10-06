package BankApplication;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Admin;
import models.transactionManagement;

import java.io.IOException;

import models.Admin;
/**
 * Servlet implementation class transferAmount
 */
@WebServlet("/transferAmount")
public class transferAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transferAmount() {
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
		
	    if(session != null && session.getAttribute("admin")!=null)  {
	    	Admin admin = (Admin) session.getAttribute("admin");
		    
		    
		    request.setAttribute("empName", admin.getName());
		    request.setAttribute("empEmail", admin.getEmail());

		    RequestDispatcher rd = request.getRequestDispatcher("transferAmount.jsp");
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
		
		
		int senderAccountNumber;
		int receiverAccountNumber;
		int amount;
		try {
			senderAccountNumber = Integer.parseInt(request.getParameter("sacno"));
			receiverAccountNumber = Integer.parseInt(request.getParameter("racno"));
			amount = Integer.parseInt(request.getParameter("amount"));
			
			if(amount<=0||senderAccountNumber<=0||receiverAccountNumber<=0) {
				request.setAttribute("msg", "Please enter valid amount/account number");
				doGet(request,response);
				return;
				
			}
			
			else {
				    String message = transactionManagement.handleTransferAmount(senderAccountNumber,receiverAccountNumber,amount);
				
					request.setAttribute("msg", message);
					doGet(request,response);
				
			}
			
			} catch(NumberFormatException e) {
				request.setAttribute("msg", "Please enter only numbers");
				doGet(request,response);
			}
		
		
	}

}
