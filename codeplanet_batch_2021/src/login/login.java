package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
	
		String uname=req.getParameter("uname");
		String pswd=req.getParameter("pswd");
		String email=req.getParameter("email");
		dbCodeLogin(uname,pswd,email,req,res);
		
		
		
	}
	private void dbCodeLogin(String uname, String pswd,String email, HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/kj","root","krrish");  
			//here kj is database name, root is username and password  
			PreparedStatement pst=con.prepareStatement("select * from user where uname = ? and pswd= ?");
			pst.setString(1,uname);
			pst.setString(2,pswd);
			ResultSet rs=pst.executeQuery();
			if(!rs.next())
			{
				
				PrintWriter out = res.getWriter();
				out.print("<h1>username or password is incorrect</h1>");
				RequestDispatcher dispatcher=req.getRequestDispatcher("login.html");
				dispatcher.include(req, res);
				
			}
			else
			{
				PrintWriter out = res.getWriter();
				out.print("<h1>successfully login</h1>");
				req.setAttribute("email", email);
				RequestDispatcher dispatcher=req.getRequestDispatcher("first.jsp");
				dispatcher.include(req, res);
			} 
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
	}
	
}
