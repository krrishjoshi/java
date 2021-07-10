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

public class updatePassword extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static void dbCodeSignUp(int otp,String pswd,HttpServletRequest req, HttpServletResponse res)
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/kj","root","krrish");  
		PreparedStatement pst=con.prepareStatement("select * from user where otp= ? ");
		pst.setInt(1,otp);
		ResultSet rs=pst.executeQuery();
		if(rs.next())
		{
			PreparedStatement pst1=con.prepareStatement("update user set pswd=? where otp=?");
			pst1.setString(1, pswd);
			pst1.setInt(2, otp);
			int i=pst1.executeUpdate();
			System.out.println(i);
			PrintWriter out = res.getWriter();
			out.print("<p1>Password has changed successfully</p1>"); 
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.html");
			dispatcher.include(req, res);
		}
		else
		{
			PrintWriter out = res.getWriter();
			out.print("<p1>otp is invalid</p1>"); 
			RequestDispatcher dispatcher=req.getRequestDispatcher("forget.jsp");
			dispatcher.include(req, res);
		} 
		con.close();  
		}catch(Exception e){ System.out.println(e);}  
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
	
		int otp=Integer.parseInt(req.getParameter("otp"));
		String pswd=req.getParameter("pswd");
		dbCodeSignUp(otp,pswd,req,res);

		
	}
}
