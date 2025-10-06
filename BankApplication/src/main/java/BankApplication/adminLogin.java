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

		
		Connection con = connection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql_stmt = "SELECT * from employee WHERE email=?";
		try {
			pstmt = con.prepareStatement(sql_stmt);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String passwordFromDB = rs.getString("password");
				if(password.equals(passwordFromDB)) {
					
					Admin admin = createAdmin(rs);
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
			
			else {
				request.setAttribute("msg", "Invalid email/password");
				doGet(request,response);
			}
			
		} catch(SQLException e) {
			request.setAttribute("msg", "Error in Database!");
			
		}
		finally {
			try {
				con.close();
				pstmt.close();
				rs.close();
			} catch(SQLException e) {e.printStackTrace();}
		}
	}

	private Admin createAdmin(ResultSet rs) {
		// TODO Auto-generated method stub
		
		try {
			
			int eid = rs.getInt("eid");
			String nameFromDB = rs.getString("name");
			String userNameFromDB = rs.getString("username");
			String emailFromDB = rs.getString("email");
			String passwordFromDB = rs.getString("password");
			String phoneNumberFromDB = rs.getString("phoneno");
			
			Admin admin = new Admin(eid,nameFromDB,userNameFromDB,emailFromDB,passwordFromDB,phoneNumberFromDB);
			
			return admin;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



}
