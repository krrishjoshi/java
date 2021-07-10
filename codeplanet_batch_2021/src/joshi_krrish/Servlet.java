package joshi_krrish;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static void dbCodeSignUp(String uname,String email,String pswd,HttpServletRequest req, HttpServletResponse res)
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/kj","root","krrish");  
		//here kj is database name, root is username and password  
		PreparedStatement pst=con.prepareStatement("select * from user where uname= ? or email= ?");
		pst.setString(1,uname);
		pst.setString(2,email);
		ResultSet rs=pst.executeQuery();
		if(!rs.next())
		{
			PreparedStatement pst1=con.prepareStatement("insert into user(uname,email,pswd) values(?,?,?)");
			pst1.setString(1, uname);
			pst1.setString(2, email);
			pst1.setString(3, pswd);
			int i=pst1.executeUpdate();
			System.out.println(i);
			PrintWriter out = res.getWriter();
			out.print("<h1>succesfully register</h1>");
			req.setAttribute("email", email);
			
			RequestDispatcher dispatcher=req.getRequestDispatcher("first.jsp");
			dispatcher.include(req, res);
		}
		else
		{
			PrintWriter out = res.getWriter();
			out.print("<h1>username or email id exist</h1>");
			RequestDispatcher dispatcher=req.getRequestDispatcher("index.html");
			dispatcher.include(req, res);
		} 
		con.close();  
		}catch(Exception e){ System.out.println(e);}  
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
	
		String uname=req.getParameter("uname");
		String email=req.getParameter("email");
		String pswd=req.getParameter("pswd");
		dbCodeSignUp(uname,email,pswd,req,res);
	}
	
}